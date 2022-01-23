package com.example.diskscheduling;

/**
 * @author JasonGu
 * @date 2021/12/3 12:27
 */

import Algorithm.C_SCAN;
import Algorithm.FCFS;
import Algorithm.SCAN;
import Algorithm.SSTF;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import Main.Out;
import model.Position;

import java.util.ArrayList;
import java.util.Random;

import static Main.Out.*;
import static Main.Out.outC_SCAN0;

public class GUI extends Application {
    public static FCFS fcfs;
    public static SSTF sstf;
    public static SCAN scan1;
    public static SCAN scan0;
    public static C_SCAN c_scan1;
    public static C_SCAN c_scan0;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        LineChart<Number, Number> lineChart =  getView();
        lineChart.setPrefWidth(1500);
        lineChart.setPrefHeight(1000);
        //图形化界面
        Button button = new Button("刷新数据");

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(button, lineChart);

        AnchorPane root = new AnchorPane();
        root.getChildren().addAll(vBox);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setWidth(1600);
        stage.setHeight(1055);
        stage.setAlwaysOnTop(true);
        stage.setTitle("磁盘调度-算法比较");
        stage.show();

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                LineChart<Number, Number> new_lineChart = getView();
                new_lineChart.setPrefWidth(1500);
                new_lineChart.setPrefHeight(1000);
                vBox.getChildren().remove(1);
                vBox.getChildren().add(new_lineChart);

            }
        });
    }

    public LineChart<Number, Number> getView(){

        // 生成数据
        Random random = new Random();
        // 初始磁道位置
        Position initPosition = new Position(random.nextInt(1500));
        ArrayList<Position> positionArrayList = new ArrayList<>();
        int n = Out.n;
        // 50% -> 0~499
        for(int i=0;i<n/2;i++){
            Position temp = new Position(random.nextInt(500));
            positionArrayList.add(temp);
        }
        // 25% -> 500,999
        for(int i=0;i<n/4;i++){
            Position temp = new Position(random.nextInt(500)+500);
            positionArrayList.add(temp);
        }
        // 25% -> 1000,1500
        for(int i=0;i<n/4;i++){
            Position temp = new Position(random.nextInt(500)+1000);
            positionArrayList.add(temp);
        }

        // 洗牌算法
        int length = positionArrayList.size();
        for(int i=0;i<length;i++){
            int iRandNum = (int)(Math.random() * length);
            Position temp = positionArrayList.get(iRandNum);
            positionArrayList.set(iRandNum, positionArrayList.get(i));
            positionArrayList.set(i,temp);
        }

        System.out.println("初始点: " + initPosition);
        System.out.println("创建的序列: " + positionArrayList);

        fcfs = outFCFS(initPosition, positionArrayList);
        sstf = outSSTF(initPosition, positionArrayList);
        scan1 = outSCAN1(initPosition, positionArrayList);
        scan0 = outSCAN0(initPosition, positionArrayList);
        c_scan1 = outC_SCAN1(initPosition, positionArrayList);
        c_scan0 = outC_SCAN0(initPosition, positionArrayList);

        // 绘图
        NumberAxis x = new NumberAxis("index",0, n,1); // 间距为20
        NumberAxis y = new NumberAxis("道数",0,1500,10);

        LineChart<Number, Number> lineChart = new LineChart<>(x,y);

        XYChart.Series<Number, Number> fcfs_line = new XYChart.Series<>();
        fcfs_line.setName("FCFS");

        XYChart.Series<Number, Number> scan1_line = new XYChart.Series<>();
        scan1_line.setName("SCAN[增大]");

        XYChart.Series<Number, Number> scan0_line = new XYChart.Series<>();
        scan0_line.setName("SCAN[减小]");

        XYChart.Series<Number, Number> c_scan1_line = new XYChart.Series<>();
        c_scan1_line.setName("C-SCAN[增大]");

        XYChart.Series<Number, Number> c_scan0_line = new XYChart.Series<>();
        c_scan0_line.setName("C-SCAN[减小]");

        XYChart.Series<Number, Number> sstf_line = new XYChart.Series<>();
        sstf_line.setName("SSTF");

        for(int i=0;i<n; i++){
            XYChart.Data<Number, Number> temp0 = new XYChart.Data<>(i,fcfs.drawFlow.get(i).getLocation());
            fcfs_line.getData().add(temp0);

            XYChart.Data<Number, Number> temp2 = new XYChart.Data<>(i,scan1.drawFlow.get(i).getLocation());
            scan1_line.getData().add(temp2);

            XYChart.Data<Number, Number> temp3 = new XYChart.Data<>(i,scan0.drawFlow.get(i).getLocation());
            scan0_line.getData().add(temp3);

            XYChart.Data<Number, Number> temp4 = new XYChart.Data<>(i,c_scan1.drawFlow.get(i).getLocation());
            c_scan1_line.getData().add(temp4);

            XYChart.Data<Number, Number> temp5 = new XYChart.Data<>(i,c_scan0.drawFlow.get(i).getLocation());
            c_scan0_line.getData().add(temp5);

            XYChart.Data<Number, Number> temp1 = new XYChart.Data<>(i,sstf.drawFlow.get(i).getLocation());
            sstf_line.getData().add(temp1);
        }

        lineChart.getData().add(fcfs_line);
        lineChart.getData().add(scan1_line);
        lineChart.getData().add(scan0_line);
        lineChart.getData().add(c_scan1_line);
        lineChart.getData().add(c_scan0_line);
        lineChart.getData().add(sstf_line);
        return lineChart;
    }

}

