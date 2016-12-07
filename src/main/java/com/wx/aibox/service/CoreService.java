package com.wx.aibox.service;


import com.wx.aibox.dao.Qadao;
import com.wx.aibox.model.QaModel;
import com.wx.aibox.model.resp.TextMessage;
import com.wx.aibox.tools.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;



/**
 * 处理微信发来的信息
 * @author Sunlight
 *
 */
@Service
public class CoreService {
    private  static final Logger logger= LoggerFactory.getLogger(CoreService.class);

    @Autowired
    Qadao qadao;


    public  String processRequest(String msg) {
        String respMessage = null;
        try {
            // 默认返回的文本消息内容
            String respContent = "请求处理异常，请稍候尝试！";

            // xml请求解析
            Map<String, String> requestMap = MessageUtil.parseXml(msg);

            System.out.println("Event=="+requestMap.get("Event"));

            // 发送方帐号（open_id）
            String fromUserName = requestMap.get("FromUserName");
            // 公众帐号
            String toUserName = requestMap.get("ToUserName");
            // 消息类型
            String msgType = requestMap.get("MsgType");

            // 回复文本消息
            TextMessage textMessage = new TextMessage();
            textMessage.setToUserName(fromUserName);
            textMessage.setFromUserName(toUserName);
            textMessage.setCreateTime(new Date().getTime());
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
            textMessage.setFuncFlag(0);

            // 文本消息
            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
                String content = requestMap.get("Content");
                try {
                    if (content.trim().substring(0,2).equals("问题")) {
                        String[] str = content.trim().split("&");
                        logger.info("split {}",content.trim().split("&"));
                        logger.info("split toString {}",str.toString());
                        QaModel qaModel = new QaModel();
                        qaModel.setUsername(fromUserName);
                        qaModel.setProblem(str[1]);
                        qaModel.setEmail(str[2]);
                        logger.info("参数",qaModel.toString());
                        qadao.insert(qaModel);
                       List<QaModel> list= qadao.findByName(fromUserName);
                        respContent = "您的问题有：";
                        for (QaModel qaModel1:list){
                            respContent+=qaModel1.getId()+"\n";
                        }
                    } else if (content.trim().substring(0,2).equals("答案")) {
                        String[] str = content.split("&");
                       QaModel qaModel= qadao.findById(Integer.valueOf(str[1]));
                        respContent = "问题"+qaModel.getProblem()+"答案："+ qaModel.getAnswer()+"\n" +
                                "请发送（答案&id）来查询";
                    } else {
                        respContent = "Sunlight提示：您发送的是文本消息！内容是：" + content;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    logger.error(e.toString());
                    respContent="你发送的数据格式有误";
                }
            }
            // 图片消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
                respContent = "Sunlight提示：您发送的是图片消息！";
            }
            // 地理位置消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
                respContent = "Sunlight提示：您发送的是地理位置消息！";
            }
            // 链接消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
                respContent = "Sunlight提示：您发送的是链接消息！";
            }
            // 音频消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
                respContent = "Sunlight提示：您发送的是音频消息！";
            }
            // 事件推送
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
                // 事件类型
                String eventType = requestMap.get("Event");
                // 自定义菜单点击事件
                if (eventType.equalsIgnoreCase(MessageUtil.EVENT_TYPE_CLICK)) {
                    // 事件KEY值，与创建自定义菜单时指定的KEY值对应
                    String eventKey = requestMap.get("EventKey");
                    System.out.println("EventKey="+eventKey);
                    respContent = "Sunlight提示：您点击的菜单KEY是"+eventKey;
                }
                if (eventType.equalsIgnoreCase(MessageUtil.EVENT_TYPE_SUBSCRIBE)){
                    respContent="感谢您订阅AIBOX！\n我们将和您一起步入人工智能的殿堂，一起学习一起进步。\n我们将用" +
                            "python作为开发语言有兴趣的小伙伴可以一起研究。\n\n" +
                            "你还可以发下列菜单："
                            + "\n1.您可发送 （问题&内容&邮箱）来提交您的问题，我们会尽快处理"
                             + "\n2.您可发送 （行业信息）获得最新资讯。";
                }
                if (eventType.equalsIgnoreCase(MessageUtil.EVENT_TYPE_SUBSCRIBE)){
                    logger.info(" 用户 {} 取消订阅",fromUserName);
                    //取消订阅订阅
                }
            }

            textMessage.setContent(respContent);
            respMessage = MessageUtil.textMessageToXml(textMessage);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
            respMessage="有异常了。。。";
        }
        return respMessage;
    }

}