package com.example.spring_jscode.controller;

import com.example.spring_jscode.dto.BoardRequestDto;
import com.example.spring_jscode.dto.BoardResponseDto;
import com.example.spring_jscode.entity.Board;
import com.example.spring_jscode.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;

    @PostMapping("")
    public ResponseEntity createBoard(@RequestBody BoardRequestDto boardRequestDto) {
        return ResponseEntity.status(201)
                .body(boardService.create(boardRequestDto.getTitle(), boardRequestDto.getContent()));
    }

    @GetMapping("")
    public ResponseEntity findAll() {
        return ResponseEntity.ok()
                .body(boardService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity findBoard(@PathVariable("id") Long id) {
        return ResponseEntity.ok()
                .body(boardService.findById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity updateBoard(@PathVariable("id") Long id,
                                      @RequestBody BoardRequestDto boardRequestDto) {
        return ResponseEntity.ok()
                .body(boardService.update(id, boardRequestDto.getTitle(), boardRequestDto.getContent()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteBoard(@PathVariable("id") Long id) {
        boardService.delete(id);
        return ResponseEntity.ok("성공적으로 제거했습니다.");
    }
}