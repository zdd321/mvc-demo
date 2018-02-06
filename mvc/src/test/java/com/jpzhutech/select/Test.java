package com.jpzhutech.select;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test {

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        try {
            //String url = "jdbc:mysql://192.168.101.44/amon";
            String url = "jdbc:postgresql://192.168.20.230:5432/newupload";//�����Լ�PostgreSQL���ݿ�ʵ�����ڵ�ip��ַ���������Լ��Ķ˿�
            //String user = "root";
            String user = "postgres";
            //String password = "560128";
            String password = "1234";  //�������ҵ�����Ϊ�գ����߿����Լ�ѡ���Ƿ���������
            //Class.forName("com.mysql.jdbc.Driver");
            Class.forName("org.postgresql.Driver");  //һ��Ҫע��������MySQL�﷨��ͬ
            connection= DriverManager.getConnection(url, user, password);
            System.out.println("�Ƿ�ɹ�����pg���ݿ�"+connection);
            String sql = "select * from biz_fsbean";
            statement = connection.createStatement();
            /**
             * ����ResultSet����⣺Java���������ݿ��ѯ�����չ����ʽ������˵�õ���һ��������ı�
             * ���ĵ��Ŀ�ʼ��������ϸ�Ľ���ýӿ���Ӧ��ע������⣬���Ķ�JDK
             * */
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                 //ȡ����ֵ
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                System.out.println(id+","+name+",");

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally{
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }finally{
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }

        }
    }

}