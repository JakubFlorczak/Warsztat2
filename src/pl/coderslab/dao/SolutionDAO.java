package pl.coderslab.dao;


import pl.coderslab.model.Solution;
import java.sql.*;
import java.util.Arrays;

public class SolutionDAO {

    private static final String CREATE_SOLUTION_QUERY = "INSERT INTO solution(created, updated,description) VALUES (?, ?, ?)";
    private static final String READ_SOLUTION_QUERY = "SELECT * FROM solution where id = ?";
    private static final String UPDATE_SOLUTION_QUERY = "UPDATE solution SET created = ?, updated = ?, description = ? where id = ?";
    private static final String DELETE_SOLUTION_QUERY = "DELETE FROM solution WHERE id = ?";
    private static final String FIND_ALL_SOLUTION_QUERY = "SELECT * FROM solution";
    private static final String FIND_ALL_SOLUTION_BY_USERID_QUERY = " Select * from solution where users_id = ?";
    private static final String FIND_ALL_EXERCISE_BY_USERSID_QUERY = "SELECT * FROM SOLUTION WHERE EXERCISE_ID = ? ORDER BY CREATED DESC";

    public Solution create(Solution solution) {
        try (Connection conn = DButil.connect()) {
            PreparedStatement statement =
                    conn.prepareStatement(CREATE_SOLUTION_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, solution.getCreated());
            statement.setString(2, solution.getUpdated());
            statement.setString(3, solution.getDescription());
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
                solution.setCreated(resultSet.getString("created"));
                solution.setUpdated(resultSet.getString("updated"));
                solution.setDescription(resultSet.getString("description"));
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
            statement.setString(1, solution.getCreated());
            statement.setString(2, solution.getUpdated());
            statement.setString(3, solution.getDescription());
            statement.setInt(4, solution.getId());
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
                solution.setCreated(resultSet.getString("created"));
                solution.setUpdated(resultSet.getString("updated"));
                solution.setDescription(resultSet.getString("description"));
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
                solution.setCreated(resultSet.getString("created"));
                solution.setUpdated(resultSet.getString("updated"));
                solution.setDescription(resultSet.getString("description"));
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
                solution.setCreated(resultSet.getString("created"));
                solution.setUpdated(resultSet.getString("updated"));
                solution.setDescription(resultSet.getString("description"));
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