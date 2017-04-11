package com.yaojiafeng.exportgateway.test;

import com.yaojiafeng.exportgateway.common.utils.EncryptUtils;
import com.yaojiafeng.exportgateway.common.utils.HttpUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/7/11 下午6:25 $
 */
@RequestMapping
public class TestMain {

    @org.junit.Test
    public void testXml() throws DocumentException {
        String text = "<csdn><java>Java班</java></csdn>";
        Document document = DocumentHelper.parseText(text);
        Element root = document.getRootElement();
        System.out.println(root.getName());

        Element csdn = root.element("java");
        System.out.println(csdn.getData());


        Element books = root.addElement("books");
        books.addAttribute("id", "001");
        books.addText("java");
        System.out.println(root.asXML());

    }

    @org.junit.Test
    public void testUrlEncode() throws UnsupportedEncodingException {
        String enc = "UTF-8";

        String encode = URLEncoder.encode("asd=a c", enc);
        System.out.println(encode);

        String decode = URLDecoder.decode(encode, enc);
        System.out.println(decode);
    }

    @org.junit.Test
    public void testXml2() throws Exception {
        Document doc = DocumentHelper.createDocument();
        //增加根节点
        Element books = doc.addElement("books");
        //增加子元素
        Element book1 = books.addElement("book");
        Element title1 = book1.addElement("title");
        Element author1 = book1.addElement("author");

        Element book2 = books.addElement("book");
        Element title2 = book2.addElement("title");
        Element author2 = book2.addElement("author");

        //为子节点添加属性
        book1.addAttribute("id", "001");
        //为元素添加内容
        title1.setText("Harry Potter");
        author1.setText("J K. Rowling");

        book2.addAttribute("id", "002");
        title2.setText("Learning XML");
        author2.setText("Erik T. Ray");

        //实例化输出格式对象
        OutputFormat format = OutputFormat.createPrettyPrint();
        //设置输出编码
        format.setEncoding("UTF-8");
        //生成XMLWriter对象，构造函数中的参数为需要输出的文件流和格式
        XMLWriter writer = new XMLWriter(System.out, format);
        //开始写入，write方法中包含上面创建的Document对象
        writer.write(doc);
    }


    @Test
    public void testmMd5() throws UnsupportedEncodingException {
        String md5key = "d61b2a012014b9b5648579d406b190b4";
        String content = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" +
                " <QueryReceipt>" +
                "<SequenceId>12312321321</SequenceId><ThirdpartyId>6</ThirdpartyId><ThirdpartyKey>623423</ThirdpartyKey><DealId>1003</DealId>" +
                "<SerialList><Serial>1234526743</Serial>" +
                "<Serial>1234526343</Serial></SerialList>" +
                "<OrderId>100234343</OrderId></QueryReceipt>";

        String xml = "xml=" + content + md5key;

        String md5 = EncryptUtils.MD5(xml);

        System.out.println(md5);

        System.out.println(URLEncoder.encode(content, "UTF-8"));
    }


    @Test
    public void testInvokeDianping() throws UnsupportedEncodingException {
        String md5key = "d61b2a012014b9b5648579d406b190b4";

        String content = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" +
                " <QueryReceipt>" +
                "<SequenceId>12312321321</SequenceId><ThirdpartyId>6</ThirdpartyId><ThirdpartyKey>623423</ThirdpartyKey><DealId>1003</DealId>" +
                "<SerialList><Serial>1234526743</Serial>" +
                "<Serial>1234526343</Serial></SerialList>" +
                "<OrderId>100234343</OrderId></QueryReceipt>";
        String xml = "xml=" + content + md5key;

        String sign = EncryptUtils.MD5(xml);

        String encode = URLEncoder.encode(content, "UTF-8");

        String body = "xml=" + encode + "&sign=" + sign;

        String post = HttpUtils.post("https://e.51ping.com/thirdparty/queryreceipt", body);

        System.out.println(post);

    }


    private static final Logger logger = LoggerFactory.getLogger(TestMain.class);

    @Test
    public void test() throws InterruptedException, IOException, NoSuchMethodException {

    }

}
