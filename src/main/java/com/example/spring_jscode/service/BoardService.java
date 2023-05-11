package com.example.spring_jscode.service;

import com.example.spring_jscode.dto.BoardResponseDto;
import com.example.spring_jscode.entity.Board;
import com.example.spring_jscode.repository.BoardRepository;
import lombok.NoArgsConstructor;
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
        boardRepository.save(Board.createBoard(title, content));
        return BoardResponseDto.fromEntity(title, content);
    }

    @Transactional(readOnly = true)
    public List<BoardResponseDto> findAll() {
        List<Board> boardList = boardRepository.findAll();
        List<BoardResponseDto> responseBoardList = new ArrayList<>();
        for(Board b : boardList) {
            responseBoardList.add(BoardResponseDto.fromEntity(b.getTitle(), b.getContent()));
        }
        return responseBoardList;
    }

    @Transactional(readOnly = true)
    public BoardResponseDto findById(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(NullPointerException::new);
        return BoardResponseDto.fromEntity(board.getTitle(), board.getContent());
    }

    public BoardResponseDto update(Long id, String title, String content) {
        Board board = boardRepository.findById(id).orElseThrow(NullPointerException::new);
        board.updateTitleAndContent(title, content);
        // boardRepository.save(board);
        return BoardResponseDto.fromEntity(title, content);
    }

    public void delete(Long id) {
        boardRepository.deleteById(id);
    }
}
