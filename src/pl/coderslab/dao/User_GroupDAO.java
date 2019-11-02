package pl.coderslab.dao;



import pl.coderslab.model.User_group;

import java.sql.*;
import java.util.Arrays;

public class User_GroupDAO {

    private static final String CREATE_GROUP_QUERY ="INSERT INTO group(name) VALUES (?)";
    private static final String READ_GROUP_QUERY ="SELECT * FROM group where id = ?";
    private static final String UPDATE_GROUP_QUERY ="UPDATE group SET name = ? where id = ?";
    private static final String DELETE_GROUP_QUERY ="DELETE FROM group WHERE id = ?";
    private static final String FIND_ALL_GROUP_QUERY ="SELECT * FROM group";


    public User_group create(User_group group) {
        try (Connection conn = DButil.connect()) {
            PreparedStatement statement =
                    conn.prepareStatement(CREATE_GROUP_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, group.getName());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                group.setId(resultSet.getInt(1));
            }
            return group;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public User_group read(int groupId) {
        try (Connection conn = DButil.connect()) {
            PreparedStatement statement = conn.prepareStatement(READ_GROUP_QUERY);
            statement.setInt(1, groupId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User_group group = new User_group();
                group.setId(resultSet.getInt("id"));
                group.setName(resultSet.getString("name"));
                return group;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(User_group group) {
        try (Connection conn = DButil.connect()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_GROUP_QUERY);
            statement.setString(1, group.getName());
            statement.setInt(2, group.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int groupId) {
        try (Connection conn = DButil.connect()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_GROUP_QUERY);
            statement.setInt(1, groupId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User_group[] findAll() {
        try (Connection conn = DButil.connect()) {
            User_group[] groups = new User_group[0];
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_GROUP_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User_group group = new User_group();
                group.setId(resultSet.getInt("id"));
                group.setName(resultSet.getString("name"));
                groups = addToArray(group, groups);
            }
            return groups;
        } catch (SQLException e) {
            e.printStackTrace(); return null;
        }
    }

    private User_group[] addToArray(User_group g, User_group[] groups) {
        User_group[] tmpGroup = Arrays.copyOf(groups, groups.length + 1);
        tmpGroup[groups.length] = g;
        return tmpGroup;
    }
}
