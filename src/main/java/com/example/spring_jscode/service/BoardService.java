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

    public Board create(String title, String content) {
        Board board = boardRepository.save(new Board(title, content));
        return board;
    }

    @Transactional(readOnly = true)
    public List<BoardResponseDto> findAll() {
        List<Board> boardList = boardRepository.findAll();
        List<BoardResponseDto> responseBoardList = new ArrayList<>();
        for(Board b : boardList) {
            BoardResponseDto dto = new BoardResponseDto();
            dto.changeBoardToDto(b.getTitle(), b.getContent());
            responseBoardList.add(dto);
        }
        return responseBoardList;
    }

    @Transactional(readOnly = true)
    public Board findById(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(NullPointerException::new);
        return board;
    }

    public Board update(Long id, String title, String content) {
        Board board = boardRepository.findById(id).orElseThrow(NullPointerException::new);
        board.setTitle(title);
        board.setContent(content);
        boardRepository.save(board);
        return board;
    }

    public void delete(Long id) {
        boardRepository.deleteById(id);
    }
}
