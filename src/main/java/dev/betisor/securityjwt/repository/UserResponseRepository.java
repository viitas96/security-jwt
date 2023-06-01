package dev.betisor.securityjwt.repository;

import dev.betisor.securityjwt.dto.ResponseProjection;
import dev.betisor.securityjwt.entity.UserResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserResponseRepository extends JpaRepository<UserResponse, Long> {

    boolean existsByQuestionIdAndUserId(Long questionId, Long userId);

    @Query("""
            select sum(case when userResponse.response = 1 then 1 else 0 end) as firstValue,
            sum(case when userResponse.response = 2 then 1 else 0 end) as secondValue,
            sum(case when userResponse.response = 3 then 1 else 0 end) as thirdValue,
            sum(case when userResponse.response = 4 then 1 else 0 end) as fourthValue
            from UserResponse userResponse
            where userResponse.question.id = ?1
            """)
    ResponseProjection countValues(Long questionId);

}
