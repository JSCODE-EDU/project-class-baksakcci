package com.example.spring_jscode.repository;

import com.example.spring_jscode.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    Page<Board> findAllByOrderByCreateDateDesc(Pageable pageable);
    Page<Board> findBoardByTitleContainingOrderByCreateDateDesc(String keyword, Pageable pageable);
}
