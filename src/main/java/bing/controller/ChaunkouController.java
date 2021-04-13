package bing.controller;

import bing.model.Dingdandao;
import bing.service.TimestampConvertsTime;
import gnu.io.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

//串口接收数据类
public class ChaunkouController implements Initializable {
    private static final Logger logger = Logger.getLogger(ChaunkouController.class);
    @FXML
    private Text ckzhongliang;
    @FXML
    private Text dingdanid;
    @FXML
    private Text dingdantype;
    @FXML
    private Text jiaoyizhongliang;
    @FXML
    private Text jiage;
    @FXML
    private Text tijiaotime;
    @FXML
    private Text cgdian;
    @FXML
    private Text producttype;
    @FXML
    private TextField weight;
    @FXML
    private Text shuliang;
    @FXML
    private TextField transaction_value; //交易价格
    @FXML
    private TextArea remark;
    @FXML
    private DatePicker planting_time;
    @FXML
    private DatePicker trading_hour;
    @FXML
    private Button cencel;
    @FXML
    private Button affirm;

    private SerialPort mySerialport = null;
    private static final int BAUDRATE = 9600;// 波特率，默认为9600
    public static Dingdandao dingdandao = new Dingdandao();
    /**
     * 检测并获取当前设备所有的可用端口(此处可包括USB端口和蓝牙端口)
     *
     * @return 返回包含所有可用端口的名称的列表(如COM4 、 COM6等)
     * 可将返回的列表依次输出以查看
     * 当然也可以通过‘设备管理器-端口’来查看可用端口
     */
    public ArrayList<String> findPorts() {
        // 调用jar包内的getPortIdentifiers函数，获得当前所有可用端口的枚举
        Enumeration<CommPortIdentifier> portList = CommPortIdentifier.getPortIdentifiers();
        ArrayList<String> portNameList = new ArrayList<String>();
        // 将可用端口名添加到List并返回该List
        while (portList.hasMoreElements()) {
            String portName = portList.nextElement().getName();
            portNameList.add(portName);
        }
        return portNameList;
    }

    /**
     * 通过上一步获取的端口名来打开串口并设置串口参数
     *
     * @param portName 端口名
     * @param baudrate 波特率(需与电子秤的波特率一致，一般为9600，建议作为final宏观常量放在程序开头)
     * @return 返回打开的串口, 若非串口则返回null
     * @throws PortInUseException 当端口已被占用时抛出异常
     */
    public SerialPort openPort(String portName, int baudrate) throws PortInUseException {
        try {
            // 通过端口名识别端口
            CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
            // 打开端口，并给端口名字和一个timeout（打开操作的超时时间）
            CommPort commPort = portIdentifier.open(portName, 2000);
            // 判断端口是不是串口
            if (commPort instanceof SerialPort) {
                SerialPort serialPort = (SerialPort) commPort;
                try {
                    // 设置一下串口的波特率等参数
                    // 数据位：8
                    // 停止位：1
                    // 校验位：None
                    serialPort.setSerialPortParams(baudrate, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
                } catch (UnsupportedCommOperationException e) {
                    e.printStackTrace();
                }
                return serialPort;
            }
        } catch (NoSuchPortException e1) {
            e1.printStackTrace();
        }
        return null;
    }

    /**
     * 为打开的串口添加数据到达事件监听、通信中断监听
     *
     * @param serialPort 已打开的串口
     * @param listener   监听器
     */
    public void addListener(SerialPort serialPort, DataAvailableListener listener) {
        try {
            /**
             * 给串口添加监听器
             * 函数addEventListener为jar包自带函数
             * 函数addEventListener的参数listener必须为SerialPortEventListener类型
             * 所以DataAvailableListener必须实现SerialPortEventListener接口
             */
            serialPort.addEventListener(listener);
            // 设置当有数据到达时唤醒监听接收线程
            serialPort.notifyOnDataAvailable(true);
            // 设置当通信中断时唤醒中断线程
            serialPort.notifyOnBreakInterrupt(true);
        } catch (TooManyListenersException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 自定义监听器，实现jar包中定义的SerialPortEventListener接口，并覆写serialEvent方法
     */
    public class DataAvailableListener implements SerialPortEventListener {
        @Override
        public void serialEvent(SerialPortEvent serialPortEvent) {
            /**
             * 总共有10类事件可以监听
             * 此处只对两类事件进行了反应和处理
             */
            switch (serialPortEvent.getEventType()) {
                case SerialPortEvent.DATA_AVAILABLE: //接收到数据事件
                    byte[] data;
                    try {
                        if (mySerialport == null) {
                            System.out.println("串口对象为空，监听失败！");
                        } else {
                            // 读取串口数据
                            data = readFromPort(mySerialport);
                            // 将ASCII码数组转化为对应的字符串
                            String text = new String(data);
                            // 去除不必要的字符
                            text = text.replaceAll(" ", "");
                            text = text.replaceAll("\r", "");
                            text = text.replaceAll("\n", "");
                            text = text.replaceAll("\t", "");
                            if (text.length() > 0) {
                                //将处理后的重量信息打印输出
                                System.out.println(text);
                                ckzhongliang.setText(text);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        // 发生读取错误时显示错误信息后退出系统
                        closeSerialPort();
                        openSerialPort();
                    }
                    break;

                case SerialPortEvent.OUTPUT_BUFFER_EMPTY: // 2.输出缓冲区已清空
                    break;

                case SerialPortEvent.CTS: // 3.清除待发送数据
                    break;

                case SerialPortEvent.DSR: // 4.待发送数据准备好了
                    break;

                case SerialPortEvent.RI: // 5.振铃指示
                    break;

                case SerialPortEvent.CD: // 6.载波检测
                    break;

                case SerialPortEvent.OE: // 7.溢位（溢出）错误
                    break;

                case SerialPortEvent.PE: // 8.奇偶校验错误
                    break;

                case SerialPortEvent.FE: // 9.帧错误
                    break;

                case SerialPortEvent.BI: // 10.通讯中断
                    System.out.println("与串口设备通讯中断");
                    closeSerialPort();
                   openSerialPort();
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 从串口中按字节读取收到的数据
     *
     * @param serialPort 打开后有数据传达的串口
     * @return 以字节数组的形式返回收到的数据信息
     */
    public byte[] readFromPort(SerialPort serialPort) {
        InputStream in = null;
        byte[] bytes = {};// 采用字节数组保存传来的ASCII码值，方便之后转化为字符串
        try {
            in = serialPort.getInputStream();//得到串口输入流
            // 缓冲区大小为一个字节
            byte[] readBuffer = new byte[1];
            int bytesNum = in.read(readBuffer);
            while (bytesNum > 0) {
                bytes = concat(bytes, readBuffer);
                bytesNum = in.read(readBuffer);//将读取到的二进制数据存于readBuffer并返回读取到的字节数
            } //按照字节将数据加入到字节数组中
        } catch (IOException e) {
            restart(); //捕获异常并读取
        } finally {
            try {
                if (in != null) {
                    in.close();
                    in = null;
                }
            } catch (IOException e) {
                restart(); //捕获异常并读取
            }
        }
        return bytes;
    }

    /**
     * 将两字节数组合并为同一个
     *
     * @param firstArray
     * @param secondArray
     * @return 返回合并后的字节数组
     */
    public byte[] concat(byte[] firstArray, byte[] secondArray) {
        if (firstArray == null || secondArray == null) {
            return null;
        }
        byte[] bytes = new byte[firstArray.length + secondArray.length];
        System.arraycopy(firstArray, 0, bytes, 0, firstArray.length);
        System.arraycopy(secondArray, 0, bytes, firstArray.length, secondArray.length);
        return bytes;
    }

    //新建一个线程，用于串口错误后重启串口
    private Thread restartThread = new Thread(new RestartThread());
    public void restart() {
        if (!restartThread.isAlive() || restartThread.isInterrupted()) {
            restartThread.start();
        }
    }
    class RestartThread implements Runnable {
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                closeSerialPort();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                openSerialPort();
            }
        }
    }

    //关闭串口
   synchronized public void closeSerialPort() {
        if (mySerialport != null) {
            mySerialport.close();
        }
        mySerialport = null;
    }

   synchronized public void openSerialPort() {
        String commName = null;
        if (findPorts().size() > 0) {
            // 获取端口名称,默认取第一个端口
            commName = findPorts().get(0); // step_1
        }
        if (commName == null) { // 说明不存在可用端口
            //textdao.setText("没有搜寻到有效串口");
        } else {
            try {
                mySerialport = openPort(commName, BAUDRATE); //step_2
                if (mySerialport != null) {
                    System.out.println("串口已打开");
                }
            } catch (PortInUseException e) {
                System.out.println("串口已被占用！");
            }

            // 添加串口监听
            addListener(mySerialport, new DataAvailableListener()); // step_3、step_4
        }
    }

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Font myFont = null;
        try {
            myFont = Font.loadFont(new FileInputStream(new File("target/classes/size/DS-DIGIB-2.ttf")), 50);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ckzhongliang.setFont(myFont); //设置字体
        ckzhongliang.setText("0000");
        //按钮事件关闭窗口，关闭串口
        cencel.setOnMouseClicked(event -> {
            Stage primaryStage = (Stage) cencel.getScene().getWindow();
            primaryStage.close();
            closeSerialPort();
        });
        dingdanid.setText(dingdandao.getOrderno());
        dingdantype.setText("" + dingdandao.getOrdertype());
        double s = dingdandao.getTotal();
        jiage.setText(String.valueOf(s));
        TimestampConvertsTime timestampConvertsTime = new TimestampConvertsTime();
        String a = timestampConvertsTime.timestamp(String.valueOf(dingdandao.getCreatetime()));
        tijiaotime.setText("" + a);
        cgdian.setText("" + dingdandao.getPurchid());
        producttype.setText(dingdandao.getCategory());
        shuliang.setText("" + dingdandao.getAmount());
        remark.setText(dingdandao.getRemark());
        transaction_value.setText("" + dingdandao.getTotal());
        openSerialPort();
    }
    public void dataindex(ResultSet rs) throws SQLException {
        while (rs.next()) {
            dingdandao.setId(rs.getInt("id"));
            dingdandao.setOrderno(rs.getString("orderno"));
            dingdandao.setOrdertype(rs.getInt("ordertype"));
            dingdandao.setUserid(rs.getInt("userid"));
            dingdandao.setTitle(rs.getString("title"));
            dingdandao.setCategory(rs.getString("category"));
            dingdandao.setThumb(rs.getString("thumb"));
            dingdandao.setAmount(rs.getInt("amount"));
            dingdandao.setPrice(rs.getInt("price"));
            dingdandao.setTotal(rs.getDouble("total"));

            dingdandao.setReplyamount(rs.getInt("replyamount"));
            dingdandao.setReplyprice(rs.getInt("replyprice"));
            dingdandao.setReplytotal(rs.getInt("replytotal"));
            dingdandao.setStatus(rs.getInt("status"));
            dingdandao.setCreatetime(rs.getInt("createtime"));
            dingdandao.setSupplytime(rs.getString("supplytime"));
            dingdandao.setAddress(rs.getString("address"));
            dingdandao.setRemark(rs.getString("remark"));
            dingdandao.setPackger(rs.getString("package"));
            dingdandao.setSpecification(rs.getString("specification"));
            dingdandao.setPurchid(rs.getInt("purchid"));
        }
    }
}