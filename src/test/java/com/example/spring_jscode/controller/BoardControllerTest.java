package com.example.spring_jscode.controller;

import com.example.spring_jscode.dto.BoardRequestDto;
import com.example.spring_jscode.dto.BoardResponseDto;
import com.example.spring_jscode.service.BoardService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SuppressWarnings({"InnerClassMayBeStatic", "NonAsciiCharacters"})
@DisplayName("Describe: Board Controller 클래스")
@WebMvcTest(BoardController.class)
public class BoardControllerTest {
    @MockBean
    public BoardService boardService;
    @Autowired
    private MockMvc mockMvc;
    @Nested
    @DisplayName("Describe: createBoard 메서드는")
    class Describe_createBoard {
        @Nested
        @DisplayName("Context: 제목과 내용이 주어지면")
        class Context_with_title_content {
            @Test
            @DisplayName("It: 게시물을 리턴한다.")
            void it_return_board() throws Exception {
                BoardRequestDto boardRequestDto = BoardRequestDto.builder()
                        .title("제목")
                        .content("내용")
                        .build();
                BoardResponseDto boardResponseDto = new BoardResponseDto.Builder()
                        .id((long)1)
                        .title("제목")
                        .content("내용")
                        .createAt(LocalDateTime.now())
                        .build();

                doReturn(boardResponseDto).when(boardService)
                                .create(any(BoardRequestDto.class));

                mockMvc.perform(
                                MockMvcRequestBuilders.post("/boards")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(new ObjectMapper().writeValueAsString(boardRequestDto))
                        ).andDo(print())
                        .andExpect(status().is(201))
                        .andExpect(jsonPath("title", boardResponseDto.getTitle()).exists())
                        .andExpect(jsonPath("content", boardResponseDto.getContent()).exists());
            }
        }
    }
}
