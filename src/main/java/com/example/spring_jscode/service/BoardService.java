package com.example.spring_jscode.service;

import com.example.spring_jscode.dto.BoardResponseDto;
import com.example.spring_jscode.entity.Board;
import com.example.spring_jscode.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardResponseDto create(String title, String content) {
        Board board = boardRepository.save(Board.createBoard(title, content));
        return BoardResponseDto.fromEntity(board.getId(), title, content, board.getCreateDate());
    }

    @Transactional(readOnly = true)
    public List<BoardResponseDto> findAll() {
        List<Board> boardList = boardRepository.findAllByOrderByCreateDateDesc();
        List<BoardResponseDto> responseBoardList = new ArrayList<>();
        for(Board b : boardList) {
            responseBoardList.add(BoardResponseDto.fromEntity(b.getId(), b.getTitle(), b.getContent(), b.getCreateDate()));
        }
        return responseBoardList;
    }

    @Transactional(readOnly = true)
    public BoardResponseDto findById(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(NullPointerException::new);
        return BoardResponseDto.fromEntity(board.getId(), board.getTitle(), board.getContent(), board.getCreateDate());
    }

    public BoardResponseDto update(Long id, String title, String content) {
        Board board = boardRepository.findById(id).orElseThrow(NullPointerException::new);
        board.updateTitleAndContent(title, content);
        boardRepository.save(board);
        return BoardResponseDto.fromEntity(id, title, content, board.getCreateDate());
    }

    public void delete(Long id) {
        boardRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<BoardResponseDto> findBoardsBySearchingKeyword(String keyword) {
        List<Board> boardList = boardRepository.findBoardByTitleContainingOrderByCreateDateDesc(keyword);
        List<BoardResponseDto> responseBoardList = new ArrayList<>();
        for(Board b : boardList) {
            responseBoardList.add(BoardResponseDto.fromEntity(b.getId(), b.getTitle(), b.getContent(), b.getCreateDate()));
        }
        return responseBoardList;
    }
}
