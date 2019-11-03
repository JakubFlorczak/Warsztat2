package pl.coderslab.dao;


import pl.coderslab.model.Solution;
import java.sql.*;
import java.util.Arrays;

public class SolutionDAO {

    private static final String CREATE_SOLUTION_QUERY = "INSERT INTO solution(created,description, exercise_id, users_id) VALUES (now(), ?, ?,?)";
    private static final String READ_SOLUTION_QUERY = "SELECT * FROM solution where id = ?";
    private static final String UPDATE_SOLUTION_QUERY = "UPDATE solution SET updated = now(), description = ? where id = ?";
    private static final String UPDATE_UNSOLVED_SOLUTION_BY_EXERCISE_ID_AND_USERS_ID ="update solution set description = ?, updated = now() where exercise_id=? and users_id=?";
    private static final String DELETE_SOLUTION_QUERY = "DELETE FROM solution WHERE id = ?";
    private static final String FIND_ALL_SOLUTION_QUERY = "SELECT * FROM solution";
    private static final String FIND_ALL_SOLUTION_BY_USERID_QUERY = " Select * from solution where users_id = ?";
    private static final String FIND_ALL_EXERCISE_BY_USERSID_QUERY = "SELECT * FROM SOLUTION WHERE EXERCISE_ID = ? ORDER BY CREATED DESC";
    private static final String FIND_ALL_UNSOLVED_BY_USER_ID = "select * from solution where users_id = ? and description is null";

    public Solution create(Solution solution) {
        try (Connection conn = DButil.connect()) {
            PreparedStatement statement =
                    conn.prepareStatement(CREATE_SOLUTION_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, solution.getDescription());
            statement.setInt(2, solution.getExercise_id());
            statement.setInt(3, solution.getUsers_id());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                solution.setId(resultSet.getInt(1));
            }
            return solution;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Solution read(int solutionId) {
        try (Connection conn = DButil.connect()) {
            PreparedStatement statement = conn.prepareStatement(READ_SOLUTION_QUERY);
            statement.setInt(1, solutionId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Solution solution = new Solution();
                solution.setId(resultSet.getInt("id"));
                solution.setCreated(resultSet.getTimestamp("created"));
                solution.setUpdated(resultSet.getTimestamp("updated"));
                solution.setDescription(resultSet.getString("description"));
                solution.setExercise_id(resultSet.getInt("exercise_id"));
                solution.setUsers_id(resultSet.getInt("users_id"));
                return solution;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Solution solution) {
        try (Connection conn = DButil.connect()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_SOLUTION_QUERY);
            statement.setString(1, solution.getDescription());
            statement.setInt(2, solution.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateUnsolved(Solution solution) {
        try (Connection conn = DButil.connect()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_UNSOLVED_SOLUTION_BY_EXERCISE_ID_AND_USERS_ID);
            statement.setString(1, solution.getDescription());
            statement.setInt(2, solution.getExercise_id());
            statement.setInt(3, solution.getUsers_id());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void delete(int exerciseId) {
        try (Connection conn = DButil.connect()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_SOLUTION_QUERY);
            statement.setInt(1, exerciseId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Solution[] findAll() {
        try (Connection conn = DButil.connect()) {
            Solution[] solutiones = new Solution[0];
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_SOLUTION_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Solution solution = new Solution();
                solution.setId(resultSet.getInt("id"));
                solution.setCreated(resultSet.getTimestamp("created"));
                solution.setUpdated(resultSet.getTimestamp("updated"));
                solution.setDescription(resultSet.getString("description"));
                solution.setExercise_id(resultSet.getInt("exercise_id"));
                solution.setUsers_id(resultSet.getInt("users_id"));
                solutiones = addToArray(solution, solutiones);
            }
            return solutiones;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Solution[] addToArray(Solution s, Solution[] solutions) {
        Solution[] tapSolutions = Arrays.copyOf(solutions, solutions.length + 1);
        tapSolutions[solutions.length] = s;
        return tapSolutions;
    }

    public Solution[] findAllByUserId(int users_id) {
        try (Connection conn = DButil.connect()) {
            Solution[] solutiones = new Solution[0];
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_SOLUTION_BY_USERID_QUERY);
            statement.setInt(1, users_id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Solution solution = new Solution();
                solution.setId(resultSet.getInt("id"));
                solution.setCreated(resultSet.getTimestamp("created"));
                solution.setUpdated(resultSet.getTimestamp("updated"));
                solution.setDescription(resultSet.getString("description"));
                solution.setExercise_id(resultSet.getInt("exercise_id"));
                solution.setUsers_id(resultSet.getInt("users_id"));
                solutiones = addToArraybyUserId(solution, solutiones);
            }
            return solutiones;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Solution[] findAllUnsolvedByUserId(int users_id) {
        try (Connection conn = DButil.connect()) {
            Solution[] solutiones = new Solution[0];
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_UNSOLVED_BY_USER_ID);
            statement.setInt(1, users_id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Solution solution = new Solution();
                solution.setId(resultSet.getInt("id"));
                solution.setCreated(resultSet.getTimestamp("created"));
                solution.setUpdated(resultSet.getTimestamp("updated"));
                solution.setDescription(resultSet.getString("description"));
                solution.setExercise_id(resultSet.getInt("exercise_id"));
                solution.setUsers_id(resultSet.getInt("users_id"));
                solutiones = addToArraybyUserId(solution, solutiones);
            }
            return solutiones;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Solution[] addToArraybyUserId(Solution s, Solution[] solutions) {
        Solution[] tapSolutions = Arrays.copyOf(solutions, solutions.length + 1);
        tapSolutions[solutions.length] = s;
        return tapSolutions;

    }

    public Solution[] findAllByExerciseId(int exercise_id) {
        try (Connection conn = DButil.connect()) {
            Solution[] solutiones = new Solution[0];
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_EXERCISE_BY_USERSID_QUERY);
            statement.setInt(1, exercise_id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Solution solution = new Solution();
                solution.setId(resultSet.getInt("id"));
                solution.setCreated(resultSet.getTimestamp("created"));
                solution.setUpdated(resultSet.getTimestamp("updated"));
                solution.setDescription(resultSet.getString("description"));
                solution.setExercise_id(resultSet.getInt("exercise_id"));
                solution.setUsers_id(resultSet.getInt("users_id"));
                solutiones = addToArrayByExerciseId(solution, solutiones);
            }
            return solutiones;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Solution[] addToArrayByExerciseId(Solution s, Solution[] solutions) {
        Solution[] tapSolutions = Arrays.copyOf(solutions, solutions.length + 1);
        tapSolutions[solutions.length] = s;
        return tapSolutions;

    }


}