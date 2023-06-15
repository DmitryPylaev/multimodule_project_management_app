package com.digdes.java2023.repository.filter;

import com.digdes.java2023.model.task.Task;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;


public class TaskSpecification {

    public static Specification<Task> getSpec(TaskFilter taskFilter) {
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (!ObjectUtils.isEmpty(taskFilter.getName()))
                predicates.add(criteriaBuilder.equal(root.get("name"), taskFilter.getName()));

            if (!ObjectUtils.isEmpty(taskFilter.getTaskStatus()))
                predicates.add(criteriaBuilder.equal(root.get("taskStatus"), taskFilter.getTaskStatus()));

            if (!ObjectUtils.isEmpty(taskFilter.getEmployee()))
                predicates.add(criteriaBuilder.equal(root.get("employee"), taskFilter.getEmployee()));

            if (!ObjectUtils.isEmpty(taskFilter.getAuthor()))
                predicates.add(criteriaBuilder.equal(root.get("author"), taskFilter.getAuthor()));

            if (!ObjectUtils.isEmpty(taskFilter.getDeadlineMin()) && !ObjectUtils.isEmpty(taskFilter.getDeadlineMax()))
                predicates.add(criteriaBuilder.between(root.get("deadline"), taskFilter.getDeadlineMin(), taskFilter.getDeadlineMax()));

            if (!ObjectUtils.isEmpty(taskFilter.getDeadlineMax()) && !ObjectUtils.isEmpty(taskFilter.getCreateDateMax()))
                predicates.add(criteriaBuilder.between(root.get("createDate"), taskFilter.getCreateDateMin(), taskFilter.getCreateDateMax()));

            if (CollectionUtils.isEmpty(predicates)) return query.where().getRestriction();

            return query.where(criteriaBuilder.and(predicates.toArray(Predicate[]::new))).getRestriction();
        });
    }
}
