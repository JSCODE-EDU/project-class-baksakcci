package com.example.spring_jscode.repository;

import com.example.spring_jscode.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findBoardByTitleContaining(String keyword);
}
