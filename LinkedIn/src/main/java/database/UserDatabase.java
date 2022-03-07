package database;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Education;
import model.Experience;
import model.Skill;
import model.User;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserDatabase implements BaseDatabase {
    static String GET_USER = "select get_user_full_info(ii_user_id:=?)";
    static String GET_USER_DATA = "select * from get_user_info(ii_user_id:=?)";
    static String GET_EDUCATION = "select * from get_education_by_user_id(ii_user_id:=?)";
    static String GET_EXPERIENCE = "select * from get_experience_by_user_id(ii_user_id:=?)";
    static String GET_SKILL = "select * from get_skill_by_user_id(ii_user_id:=?)";

    public static void getUser(String id) {
        long start = System.currentTimeMillis();
        try (Connection connection = BaseDatabase.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_USER);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            User data = null;
            while (resultSet.next()) {
                InputStream inputStream = resultSet.getBinaryStream(1);
                ObjectMapper objectMapper = new ObjectMapper();
                data = objectMapper.readValue(inputStream, User.class);
            }
            System.out.println(data);
            long stop = System.currentTimeMillis();
            System.out.println("r: " + (stop - start));

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void getUserData(String id) {
        User userData = new User();
        long start=System.currentTimeMillis();
        try (Connection connection = BaseDatabase.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_DATA);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                userData.setFirstname(resultSet.getString("first_name"));
                userData.setLastname(resultSet.getString("last_name"));
                userData.setBirthdate(resultSet.getDate("birth_date"));
                userData.setAbout(resultSet.getString("about"));
                userData.setEmail(resultSet.getString("email"));
//                userData.setRegion(resultSet.getString("region"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (Connection connection = BaseDatabase.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_EDUCATION);
            preparedStatement.setString(1, id);
            ResultSet resultSet1 = preparedStatement.executeQuery();
            List<Education> educationList = new ArrayList<>();
            while (resultSet1.next()) {
                Education education = new Education();
                education.setName(resultSet1.getString("ed_name"));
                education.setFaculty(resultSet1.getString("ed_faculty"));
                education.setDegree(resultSet1.getString("ed_degree"));
                education.setDescription(resultSet1.getString("ed_desc"));
//                education.setRegion(resultSet.getString("region"));
//                education.setLinkedIn(resultSet.getString("linkedin"));
                education.setStartDate(resultSet1.getDate("ed_start_date"));
                education.setEndDate(resultSet1.getDate("ed_end_date"));
                educationList.add(education);
            }
            userData.setEducationList(educationList);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection connection = BaseDatabase.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_EXPERIENCE);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Experience> experienceList = new ArrayList<>();
            while (resultSet.next()) {
                Experience experience = new Experience();
                experience.setName(resultSet.getString("ex_name"));
                experience.setDescription(resultSet.getString("ex_desc"));
//                experience.setRegion(resultSet.getString("region"));
//                experience.setLinkedIn(resultSet.getString("linkedin"));
                experience.setStartDate(resultSet.getDate("ex_start_date"));
                experience.setEndDate(resultSet.getDate("ex_end_date"));
                experienceList.add(experience);
            }
            userData.setExperienceList(experienceList);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection connection = BaseDatabase.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_SKILL);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Skill> skillList = new ArrayList<>();
            while (resultSet.next()) {
                Skill skill = new Skill();
                skill.setName(resultSet.getString("skill_name"));
                skill.setCategory_name(resultSet.getString("skill_category_name"));

                skillList.add(skill);
            }
            userData.setSkillList(skillList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(userData);
        long stop=System.currentTimeMillis();
        System.out.println("r: " + (stop - start));
    }
}
