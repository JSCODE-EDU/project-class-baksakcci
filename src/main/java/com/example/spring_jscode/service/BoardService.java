package com.example.spring_jscode.service;

import com.example.spring_jscode.controller.advice.ErrorCode;
import com.example.spring_jscode.dto.BoardPageResponseDto;
import com.example.spring_jscode.dto.BoardRequestDto;
import com.example.spring_jscode.dto.BoardResponseDto;
import com.example.spring_jscode.domain.entity.Board;
import com.example.spring_jscode.domain.repository.BoardRepository;
import com.example.spring_jscode.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardResponseDto create(BoardRequestDto boardRequestDto) {
        Board board = boardRequestDto.toEntity(boardRequestDto);
        Board savedboard = boardRepository.save(board);
        return new BoardResponseDto.Builder()
                .id(savedboard.getId())
                .title(savedboard.getTitle())
                .content(savedboard.getContent())
                .createAt(savedboard.getCreateAt())
                .build();
    }

    @Transactional(readOnly = true)
    public BoardPageResponseDto findAll(Integer pageSize) {
        Page<Board> boardPage = boardRepository.findAllByOrderByCreateAtDesc(PageRequest.of(pageSize, 100));
        Page<BoardResponseDto> boardDtoPages = boardPage.map(BoardResponseDto::fromEntity);
        List<BoardResponseDto> boardDtoPagesContent = boardDtoPages.getContent();
        return BoardPageResponseDto.toDtoFromBoardResponseDto(boardDtoPagesContent, boardPage.getTotalPages(), boardPage.getNumber());
    }

    @Transactional(readOnly = true)
    public BoardResponseDto findById(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.BOARD_NOT_FOUND));
        return new BoardResponseDto.Builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .createAt(board.getCreateAt())
                .build();
    }

    public BoardResponseDto update(Long id, BoardRequestDto boardRequestDto) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.BOARD_NOT_FOUND));
        board.update(boardRequestDto.getTitle(), boardRequestDto.getContent());
        boardRepository.save(board);
        return new BoardResponseDto.Builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .createAt(board.getCreateAt())
                .build();
    }

    public void delete(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.BOARD_NOT_FOUND));
        boardRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public BoardPageResponseDto findBoardsBySearchingKeyword(String keyword, Integer pageSize) {
        Page<Board> boardPage = boardRepository.findBoardByTitleContainingOrderByCreateAtDesc(keyword, PageRequest.of(pageSize, 100));
        Page<BoardResponseDto> boardDtoPages = boardPage.map(BoardResponseDto::fromEntity);
        List<BoardResponseDto> boardDtoPagesContent = boardDtoPages.getContent();
        return BoardPageResponseDto.toDtoFromBoardResponseDto(boardDtoPagesContent, boardPage.getTotalPages(), boardPage.getNumber());
    }
}
