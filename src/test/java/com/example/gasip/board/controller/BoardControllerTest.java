package com.example.gasip.board.controller;

import com.example.gasip.board.dto.BoardCreateRequest;
import com.example.gasip.board.dto.BoardCreateResponse;
import com.example.gasip.board.dto.BoardReadResponse;
import com.example.gasip.board.service.BoardService;
import com.example.gasip.college.model.College;
import com.example.gasip.global.constant.Role;
import com.example.gasip.global.security.WithMockMember;
import com.example.gasip.major.model.Major;
import com.example.gasip.member.model.Member;
import com.example.gasip.professor.model.Professor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class BoardControllerTest {

    @InjectMocks
    private BoardController boardController;
    @MockBean
    private BoardService boardService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Test
    @WithMockMember
    void createBoardSuccess() throws Exception {
        //given
        College college = new College(1L,"AI융합대학");
        Major major = new Major(1L,"컴퓨터공학과",college);
        Professor professor = new Professor(1L,"김길동",major);
        Member member = new Member(1L,"test@test.com","hyemin","123password", Role.MEMBER);
        BoardCreateRequest request = new BoardCreateRequest("a",1L,2L,professor,member,2);
        BoardCreateResponse response = new BoardCreateResponse(1L, "a", 1L, 2);

        doReturn(response).when(boardService)
            .createBoard(any(BoardCreateRequest.class),any(),anyLong());

        //when
        ResultActions resultActions = mockMvc.perform(
            MockMvcRequestBuilders.post("/boards/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        );

        //then
        MvcResult mvcResult = resultActions.andExpect(status().isCreated()).andReturn();

    }

    @Test
    void findAllBoard() throws Exception {
        //given
        BoardReadResponse boardReadResponse1 = new BoardReadResponse(1L, "a", 1L,1L,1L, 2);
        BoardReadResponse boardReadResponse2 = new BoardReadResponse(2L, "b", 1L,1L,1L, 2);
        List<BoardReadResponse> response = new ArrayList<>();
        response.add(boardReadResponse1);
        response.add(boardReadResponse2);
        doReturn(response).when(boardService)
            .findAllBoard(any());
        //when
        ResultActions resultActions = mockMvc.perform(
            get("/boards")
                .contentType(MediaType.APPLICATION_JSON)
        );

        //then
        resultActions.andExpect(status().isOk()).andDo(print());
    }

    @Test
    void getBoardDetail() {
    }

    @Test
    void editBoard() {
    }

    @Test
    void deleteBoard() {
    }

    @Test
    void getBestBoard() {
    }
}