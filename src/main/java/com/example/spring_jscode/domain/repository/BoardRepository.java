package com.example.spring_jscode.domain.repository;

import com.example.spring_jscode.domain.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    Page<Board> findAllByOrderByCreateAtDesc(Pageable pageable);
    Page<Board> findBoardByTitleContainingOrderByCreateAtDesc(String keyword, Pageable pageable);
}
