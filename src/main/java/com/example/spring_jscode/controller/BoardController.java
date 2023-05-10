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
        Board board = boardService.create(boardRequestDto.getTitle(), boardRequestDto.getContent());
        return ResponseEntity.status(201).body(board);
    }

    @GetMapping("")
    public ResponseEntity findAll() {
        List<BoardResponseDto> boards = boardService.findAll();
        return ResponseEntity.ok().body(boards);
    }

    @GetMapping("/{id}")
    public ResponseEntity findBoard(@PathVariable("id") Long id) {
        Board board = boardService.findById(id);
        return ResponseEntity.ok().body(board);
    }

    @PatchMapping("/{id}")
    public ResponseEntity updateBoard(@PathVariable("id") Long id,
                                      @RequestBody BoardRequestDto boardRequestDto) {
        Board board = boardService.update(id, boardRequestDto.getTitle(), boardRequestDto.getContent());
        return ResponseEntity.ok().body(board);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteBoard(@PathVariable("id") Long id) {
        boardService.delete(id);
        return ResponseEntity.ok("성공적으로 제거했습니다.");
    }
}
