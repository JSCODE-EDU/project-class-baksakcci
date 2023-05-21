package com.example.spring_jscode.service;

import com.example.spring_jscode.dto.BoardPageResponseDto;
import com.example.spring_jscode.dto.BoardResponseDto;
import com.example.spring_jscode.entity.Board;
import com.example.spring_jscode.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardResponseDto create(String title, String content) {
        Board board = boardRepository.save(Board.create(title, content));
        return BoardResponseDto.fromEntity(board.getId(), title, content, board.getCreateAt());
    }

    @Transactional(readOnly = true)
    public BoardPageResponseDto findAll(Integer pageSize) {
        Page<Board> boardPage = boardRepository.findAllByOrderByCreateAtDesc(PageRequest.of(pageSize, 100));
        List<BoardResponseDto> responseBoardList = new ArrayList<>();
        for(Board b : boardPage.getContent()) {
            responseBoardList.add(BoardResponseDto.fromEntity(b.getId(), b.getTitle(), b.getContent(), b.getCreateAt()));
        }
        return BoardPageResponseDto.toDtoFromBoardResponseDto(responseBoardList, boardPage.getTotalPages(), boardPage.getNumber());
    }

    @Transactional(readOnly = true)
    public BoardResponseDto findById(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new NoSuchElementException("게시판을 찾을 수 없습니다."));
        return BoardResponseDto.fromEntity(board.getId(), board.getTitle(), board.getContent(), board.getCreateAt());
    }

    public BoardResponseDto update(Long id, String title, String content) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new NoSuchElementException("게시판을 찾을 수 없습니다."));
        board.update(title, content);
        boardRepository.save(board);
        return BoardResponseDto.fromEntity(id, title, content, board.getCreateAt());
    }

    public void delete(Long id) {
        boardRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public BoardPageResponseDto findBoardsBySearchingKeyword(String keyword, Integer pageSize) {
        Page<Board> boardPage = boardRepository.findBoardByTitleContainingOrderByCreateAtDesc(keyword, PageRequest.of(pageSize, 100));
        List<BoardResponseDto> responseBoardList = new ArrayList<>();
        for(Board b : boardPage.getContent()) {
            responseBoardList.add(BoardResponseDto.fromEntity(b.getId(), b.getTitle(), b.getContent(), b.getCreateAt()));
        }
        return BoardPageResponseDto.toDtoFromBoardResponseDto(responseBoardList, boardPage.getTotalPages(), boardPage.getNumber());
    }
}
