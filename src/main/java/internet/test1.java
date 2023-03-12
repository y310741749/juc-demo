package internet;

import cn.hutool.core.util.HexUtil;

import java.net.InetAddress;
import java.net.UnknownHostException;

//获取ip
public class test1 {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress byName = InetAddress.getByName("www.baidu.com");
        System.out.println(HexUtil.encodeHex(byName.getAddress()));
        System.out.println(byName.getHostAddress());
        System.out.println(byName.getHostName());
    }
}
