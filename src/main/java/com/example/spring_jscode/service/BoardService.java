package com.example.spring_jscode.service;

import com.example.spring_jscode.dto.BoardPageResponseDto;
import com.example.spring_jscode.dto.BoardRequestDto;
import com.example.spring_jscode.dto.BoardResponseDto;
import com.example.spring_jscode.domain.entity.Board;
import com.example.spring_jscode.domain.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardResponseDto create(BoardRequestDto boardRequestDto) {
        Board board = boardRequestDto.toEntity(boardRequestDto);
        Board savedboard = boardRepository.save(board);
        return BoardResponseDto.fromEntity(savedboard);
    }

    @Transactional(readOnly = true)
    public BoardPageResponseDto findAll(Integer pageSize) {
        Page<Board> boardPage = boardRepository.findAllByOrderByCreateAtDesc(PageRequest.of(pageSize, 100));
        Page<BoardResponseDto> boardDtoPages = boardPage.map(board -> {
            BoardResponseDto boardResponseDto = BoardResponseDto.fromEntity(board);
            return boardResponseDto;
        });
        return BoardPageResponseDto.toDtoFromBoardResponseDto(boardDtoPages, boardPage.getTotalPages(), boardPage.getNumber());
    }

    @Transactional(readOnly = true)
    public BoardResponseDto findById(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new NoSuchElementException("게시판을 찾을 수 없습니다."));
        return BoardResponseDto.fromEntity(board);
    }

    public BoardResponseDto update(Long id, String title, String content) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new NoSuchElementException("게시판을 찾을 수 없습니다."));
        board.update(title, content);
        boardRepository.save(board);
        return BoardResponseDto.fromEntity(board);
    }

    public void delete(Long id) {
        boardRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public BoardPageResponseDto findBoardsBySearchingKeyword(String keyword, Integer pageSize) {
        Page<Board> boardPage = boardRepository.findBoardByTitleContainingOrderByCreateAtDesc(keyword, PageRequest.of(pageSize, 100));
        Page<BoardResponseDto> boardDtoPages = boardPage.map(board -> {
            BoardResponseDto boardResponseDto = BoardResponseDto.fromEntity(board);
            return boardResponseDto;
        });
        return BoardPageResponseDto.toDtoFromBoardResponseDto(boardDtoPages, boardPage.getTotalPages(), boardPage.getNumber());
    }
}
