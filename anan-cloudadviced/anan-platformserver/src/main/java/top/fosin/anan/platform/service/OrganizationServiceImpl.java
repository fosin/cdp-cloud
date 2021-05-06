package top.fosin.anan.platform.service;

import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import top.fosin.anan.cloudresource.constant.RedisConstant;
import top.fosin.anan.cloudresource.constant.SystemConstant;
import top.fosin.anan.cloudresource.dto.request.AnanOrganizationCreateDto;
import top.fosin.anan.cloudresource.dto.request.AnanOrganizationRetrieveDto;
import top.fosin.anan.cloudresource.dto.request.AnanOrganizationUpdateDto;
import top.fosin.anan.cloudresource.dto.res.AnanOrganizationTreeDto;
import top.fosin.anan.core.util.TreeUtil;
import top.fosin.anan.jpa.repository.IJpaRepository;
import top.fosin.anan.platform.repository.OrganizationRepository;
import top.fosin.anan.platform.service.inter.OrganizationService;
import top.fosin.anan.platformapi.entity.AnanOrganizationEntity;
import top.fosin.anan.platformapi.service.AnanUserDetailService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 2017/12/29.
 * Time:12:31
 *
 * @author fosin
 */
@Service
@Lazy
public class OrganizationServiceImpl implements OrganizationService {
    private final OrganizationRepository organizationRepository;
    private final AnanUserDetailService ananUserDetailService;

    public OrganizationServiceImpl(OrganizationRepository organizationRepository, AnanUserDetailService ananUserDetailService) {
        this.organizationRepository = organizationRepository;
        this.ananUserDetailService = ananUserDetailService;
    }

    @Override
    @CachePut(value = RedisConstant.ANAN_ORGANIZATION, key = "#result.id")
    public AnanOrganizationEntity create(AnanOrganizationCreateDto entity) {
        Assert.notNull(entity, "传入的创建数据实体对象不能为空!");
        AnanOrganizationEntity createEntity = new AnanOrganizationEntity();
        BeanUtils.copyProperties(entity, createEntity);
        Long pid = entity.getPid();
        int level = 1;
        if (pid == 0) {
            ananUserDetailService.clear();
            Assert.isTrue(ananUserDetailService.hasSysAdminRole(), "只有超级管理员才能创建顶级机构!");
            createEntity.setTopId(0L);
        } else {
            AnanOrganizationEntity parentEntity = organizationRepository.findById(pid).orElse(null);
            Assert.notNull(parentEntity, "传入的创建数据实体找不到对于的父节点数据!");
            level = parentEntity.getLevel() + 1;
        }
        createEntity.setLevel(level);
        AnanOrganizationEntity result = organizationRepository.save(createEntity);
        if (pid == 0) {
            result.setTopId(result.getId());
            result = organizationRepository.save(result);
        }
        return result;
    }

    @Override
    @CachePut(value = RedisConstant.ANAN_ORGANIZATION, key = "#entity.id")
    public AnanOrganizationEntity update(AnanOrganizationUpdateDto entity) {
        Assert.notNull(entity, "无效的更新数据");
        Long id = entity.getId();
        Assert.notNull(id, "无效的字典代码code");
        AnanOrganizationEntity updateEntity = organizationRepository.findById(id).orElse(null);
        BeanUtils.copyProperties(entity, Objects.requireNonNull(updateEntity, "根据传入的机构ID" + id + "在数据库中未能找到对于数据!"));

        Long pid = entity.getPid();
        if (!updateEntity.getPid().equals(pid)) {
            AnanOrganizationEntity parentEntity = organizationRepository.findById(pid).orElse(null);
            updateEntity.setLevel(Objects.requireNonNull(parentEntity,
                    "传入的创建数据实体找不到对于的父节点数据!").getLevel() + 1);
        }
        return organizationRepository.save(updateEntity);
    }

    @Override
    @Cacheable(value = RedisConstant.ANAN_ORGANIZATION, key = "#id")
    public AnanOrganizationEntity findById(Long id) {
        return organizationRepository.findById(id).orElse(null);
    }

    @Override
    @CacheEvict(value = RedisConstant.ANAN_ORGANIZATION, key = "#id")
    public AnanOrganizationEntity deleteById(Long id) {
        Assert.notNull(id, "传入了空ID!");
        List<AnanOrganizationEntity> entities = findChildByPid(id);
        Assert.isTrue(entities == null || entities.size() == 0, "该节点还存在子节点不能直接删除!");
        organizationRepository.deleteById(id);
        return null;
    }

    @Override
    @CacheEvict(value = RedisConstant.ANAN_ORGANIZATION, key = "#entity.id")
    public AnanOrganizationEntity deleteByEntity(AnanOrganizationRetrieveDto entity) {
        Assert.notNull(entity, "传入了空对象!");
        Long id = entity.getId();
        Assert.notNull(id, "传入了空ID!");
        List<AnanOrganizationEntity> entities = findChildByPid(id);
        Assert.isTrue(entities == null || entities.size() == 0, "该节点还存在子节点不能直接删除!");
        AnanOrganizationEntity deleteEntity = organizationRepository.findById(id).orElse(null);
        if (deleteEntity != null) {
            organizationRepository.delete(deleteEntity);
        }
        return deleteEntity;
    }

    @Override
    public List<AnanOrganizationEntity> findAllByTopId(Long topId) {
        Assert.isTrue(topId != null && topId >= 0, "顶级机构编号无效!");
        return organizationRepository.findAllByTopId(topId);
    }

    public String getCacheName() {
        return "AllData";
    }

//    @Override
//    public Result findAllByPageSort(PageModule pageModule) {
//        PageRequest pageable = PageRequest.of(pageModule.getPageNumber() - 1, pageModule.getPageSize(), Sort.Direction.fromString(pageModule.getSortOrder()), pageModule.getSortName());
//        String searchCondition = pageModule.getSearchText();
//
//        Specification<AnanOrganizationEntity> condition = (Specification<AnanOrganizationEntity>) (root, query, cb) -> {
//            if (StringUtils.isBlank(searchCondition)) {
//                return query.getRestriction();
//            }
//            Path<String> id = root.get(SystemConstant.ID_NAME);
//            Path<String> name = root.get("name");
//            Path<String> fullname = root.get("fullname");
//            Path<String> address = root.get("address");
//            return cb.or(cb.like(id, "%" + searchCondition + "%"), cb.like(name, "%" + searchCondition + "%"), cb.like(fullname, "%" + searchCondition + "%"), cb.like(address, "%" + searchCondition + "%"));
//        };
//        //分页查找
//        Page<AnanOrganizationEntity> page = organizationRepository.findAll(condition, pageable);
//
//        return ResultUtils.success(page.getTotalElements(), page.getContent());
//    }

    @Override
    public List<AnanOrganizationEntity> findChildByPid(Long pid) {
        return organizationRepository.findByPidOrderByCodeAsc(pid);
    }

    @Override
    public List<AnanOrganizationEntity> findAllChildByPid(Long pid) {
        List<AnanOrganizationEntity> result = new ArrayList<>();
        if (pid == 0) {
            List<AnanOrganizationEntity> list = organizationRepository.findByPidOrderByCodeAsc(pid);
            for (AnanOrganizationEntity organizationEntity : list) {
                List<AnanOrganizationEntity> byCodeStartingWith = organizationRepository.findByTopIdAndCodeStartingWithOrderByCodeAsc(organizationEntity.getTopId(), organizationEntity.getCode());
                result.addAll(byCodeStartingWith);
            }
        } else {
            AnanOrganizationEntity organizationEntity = organizationRepository.findById(pid).orElse(null);
            if (organizationEntity != null) {
                List<AnanOrganizationEntity> byCodeStartingWith = organizationRepository.findByTopIdAndCodeStartingWithOrderByCodeAsc(organizationEntity.getTopId(), organizationEntity.getCode());
                result.addAll(byCodeStartingWith);
            }
        }
        return result;
    }

    @Override
    public AnanOrganizationTreeDto treeAllChildByid(Long id) {
        Assert.isTrue(id > 0, "传入ID不能小于1");
        AnanOrganizationEntity organizationEntity = organizationRepository.findById(id).orElse(null);
        List<AnanOrganizationEntity> list = organizationRepository.findByTopIdAndCodeStartingWithOrderByCodeAsc(Objects.requireNonNull(organizationEntity).getTopId(), organizationEntity.getCode());
        Assert.isTrue(list.size() > 0, "没有找到任何机构数据");
        Long rootId = organizationEntity.getId();
        List<AnanOrganizationTreeDto> dtoList = new ArrayList<>(list.size());
        for (AnanOrganizationEntity entity : list) {
            AnanOrganizationTreeDto dto = new AnanOrganizationTreeDto();
            BeanUtils.copyProperties(entity, dto);
            dto.setId(entity.getId());
            dtoList.add(dto);
        }
        AnanOrganizationTreeDto tree = TreeUtil.createTree(dtoList, rootId, SystemConstant.ID_NAME, SystemConstant.PID_NAME, SystemConstant.CHILDREN_NAME);
        setLeaf(tree);
        return tree;
    }

    private void setLeaf(AnanOrganizationTreeDto tree) {
        List<AnanOrganizationTreeDto> children = tree.getChildren();
        tree.setLeaf(children == null || children.size() == 0);
        if (children != null) {
            for (AnanOrganizationTreeDto child : children) {
                setLeaf(child);
            }
        }
    }

    @Override
    public IJpaRepository<AnanOrganizationEntity, Long> getRepository() {
        return organizationRepository;
    }
}
