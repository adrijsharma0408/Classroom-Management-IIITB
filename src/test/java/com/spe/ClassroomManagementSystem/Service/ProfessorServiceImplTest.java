package com.spe.ClassroomManagementSystem.Service;

import com.spe.ClassroomManagementSystem.Models.Login;
import com.spe.ClassroomManagementSystem.Models.Professor;
import com.spe.ClassroomManagementSystem.Repository.ProfessorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@SpringBootTest
class ProfessorServiceImplTest {
    @Autowired
    private  ProfessorService professorService;
    @MockBean
    private ProfessorRepository professorRepository;

    @Test
    void saveProfessor() {
        Login login = new Login("professor", "adrijsharma", "password");
        String userName = "adrijsharma";
        String professorName = "Shriya Kabra";
        String professorEmail = "adrijsharma@gmail.com";
        Professor professor = new Professor(userName, professorName, professorEmail , login);
        when(professorRepository.save(professor)).thenReturn(
                new Professor(userName, professorName, professorEmail , new Login("professor", "adrijsharma", "password"))
        );
        assertEquals("Saved Professor Successfully", professorService.saveProfessor(professor));

    }

    @Test
    void findByForeignId() {
        Login login = new Login("professor", "adrijsharma", "password");
        when(professorRepository.findByForeignId(login)).thenReturn(
                new Professor("adrijsharma", "Shriya Kabra", "adrijsharma@gmail.com" , new Login("professor", "adrijsharma", "password"))
        );
        assertEquals("Shriya Kabra",professorService.findByForeignId(login).getProfessorName());
    }
}