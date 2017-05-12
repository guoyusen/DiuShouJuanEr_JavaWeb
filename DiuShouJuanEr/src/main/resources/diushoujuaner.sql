/*
Navicat MySQL Data Transfer

Source Server         : LocalAdmin
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : diushoujuaner

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2017-05-12 21:35:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `AdminNo` bigint(20) NOT NULL AUTO_INCREMENT,
  `NickName` varchar(100) DEFAULT NULL,
  `AddTime` varchar(20) DEFAULT NULL,
  `Level` smallint(6) NOT NULL,
  `AdminPsd` varchar(100) NOT NULL,
  PRIMARY KEY (`AdminNo`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', '郭玉森', '2015-11-06 20:10:20', '1', '111111');

-- ----------------------------
-- Table structure for buddy
-- ----------------------------
DROP TABLE IF EXISTS `buddy`;
CREATE TABLE `buddy` (
  `BuddyNo` bigint(20) NOT NULL AUTO_INCREMENT,
  `UserNo` bigint(20) DEFAULT NULL,
  `NickName` varchar(100) DEFAULT NULL,
  `Story` varchar(4000) DEFAULT NULL,
  `Place` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`BuddyNo`),
  KEY `Reference_Buddy_User` (`UserNo`),
  CONSTRAINT `Reference_Buddy_User` FOREIGN KEY (`UserNo`) REFERENCES `user` (`UserNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of buddy
-- ----------------------------

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `CommentNo` bigint(20) NOT NULL AUTO_INCREMENT,
  `RecallNo` bigint(20) DEFAULT NULL,
  `Content` varchar(1000) NOT NULL,
  `AddTime` varchar(20) NOT NULL,
  `FromNo` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`CommentNo`),
  KEY `Refrence_Comment_Recall` (`RecallNo`),
  CONSTRAINT `Refrence_Comment_Recall` FOREIGN KEY (`RecallNo`) REFERENCES `recall` (`RecallNo`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('5', '2', '都没有人给你评论的。我来坐沙发', '2015-12-20 12:25:50', '10004');
INSERT INTO `comment` VALUES ('13', '18', '你好你好', '2015-12-25 20:08:02', '10001');
INSERT INTO `comment` VALUES ('14', '16', '丞相好', '2015-12-25 20:11:59', '10001');
INSERT INTO `comment` VALUES ('18', '18', '你好坏', '2015-12-25 20:19:16', '10004');
INSERT INTO `comment` VALUES ('19', '16', '范德萨范德萨', '2015-12-25 20:19:59', '10004');
INSERT INTO `comment` VALUES ('20', '16', '恩恩恩恩', '2015-12-25 20:20:29', '10004');
INSERT INTO `comment` VALUES ('21', '14', '哈哈哈哈哈', '2015-12-25 20:22:03', '10004');
INSERT INTO `comment` VALUES ('22', '14', '范德萨范德萨范德萨', '2015-12-25 20:22:21', '10004');
INSERT INTO `comment` VALUES ('23', '14', '烦烦烦飞凤飞飞凤飞飞凤飞飞凤飞飞凤飞飞凤飞飞凤飞飞凤飞飞凤飞飞', '2015-12-25 20:22:37', '10004');
INSERT INTO `comment` VALUES ('25', '10', '发货的经适房的时刻就发货的思考几分', '2015-12-25 20:23:22', '10004');
INSERT INTO `comment` VALUES ('26', '10', '努力的往前飞，再累也无所谓。黑夜过后的光芒有多美...我就是刘备，我要努力匡扶汉室！！！公瑾，可否助我成大事。。。。？呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵', '2015-12-25 20:23:33', '10004');
INSERT INTO `comment` VALUES ('30', '16', '范德萨减肥看第三发斯柯达', '2015-12-25 20:42:48', '10006');
INSERT INTO `comment` VALUES ('31', '10', '发的撒开飞机上打开房间的三款', '2015-12-25 20:43:19', '10006');
INSERT INTO `comment` VALUES ('34', '8', '吕布小二，三姓家奴，吾留你不得。。。杀之', '2015-12-25 22:59:13', '10001');
INSERT INTO `comment` VALUES ('39', '9', '子龙，吾甚是青睐于你。百万军中救阿斗，如此智谋，当今世上少有。他日汝可如我麾下，定是一大快事。', '2016-01-14 19:34:43', '10001');
INSERT INTO `comment` VALUES ('41', '17', 'fdfdsafdsafds', '2016-01-20 14:42:02', '10001');
INSERT INTO `comment` VALUES ('42', '14', 'fdsafdsfdsafdsa', '2016-01-20 14:43:52', '10001');
INSERT INTO `comment` VALUES ('46', '40', '大哥，刚带兵训练去了！这张自拍不错，，，有种君临天下的感觉。大哥好棒', '2016-03-26 09:42:32', '10005');
INSERT INTO `comment` VALUES ('48', '41', '先给自己赞一个！', '2016-03-26 17:55:24', '10006');
INSERT INTO `comment` VALUES ('49', '36', '今天在图书馆睡觉，好冷啊。。。快冻死宝宝了！！！', '2016-03-31 13:51:48', '10001');
INSERT INTO `comment` VALUES ('53', '47', '提提一样', '2016-04-02 16:22:11', '10001');
INSERT INTO `comment` VALUES ('55', '46', '就是喜欢胡歌  哼哼', '2016-04-02 16:29:32', '10001');
INSERT INTO `comment` VALUES ('56', '44', '好漂亮', '2016-04-02 16:31:10', '10001');
INSERT INTO `comment` VALUES ('59', '39', '如此自恋之人…… 真的没谁', '2016-04-02 17:05:36', '10001');
INSERT INTO `comment` VALUES ('61', '46', '我婆婆所以', '2016-04-02 17:11:51', '10001');
INSERT INTO `comment` VALUES ('62', '41', '～！！！！！！', '2016-04-02 17:54:16', '10001');
INSERT INTO `comment` VALUES ('63', '45', '！！～～～！！', '2016-04-02 18:01:59', '10001');
INSERT INTO `comment` VALUES ('64', '43', '绣十字绣所以我', '2016-04-02 18:35:55', '10001');
INSERT INTO `comment` VALUES ('65', '43', '～！！！～～～！！！(\"▔㉨▔)汗 ', '2016-04-02 18:37:38', '10001');
INSERT INTO `comment` VALUES ('66', '42', '我相信，你不会忘记我。。。。。', '2016-04-05 00:20:26', '10001');
INSERT INTO `comment` VALUES ('67', '48', '神乐好卡哇伊哦', '2016-04-05 18:39:19', '10002');
INSERT INTO `comment` VALUES ('68', '48', '别瞎说。。。这吗委婉', '2016-04-05 18:39:59', '10001');
INSERT INTO `comment` VALUES ('69', '48', '咯哦哦么的的', '2016-04-05 18:46:06', '10002');
INSERT INTO `comment` VALUES ('70', '50', '好棒……', '2016-04-07 22:07:01', '10003');
INSERT INTO `comment` VALUES ('71', '49', '神乐的吃相蛮好的呀……胖嘟嘟的大美女！财大的高材生……美貌和智慧兼备。对了，他们这是在吃什么？好像是鸡排啊…还有，中间这副照片是你们一起出去玩真人cs时候拍的吧～好有感觉哦。第三张照片里那个大傻子，为什么跪着办公……哈哈', '2016-04-08 00:34:29', '10006');
INSERT INTO `comment` VALUES ('72', '49', '我也来凑凑热闹……第三张照片是小郭子啊。这么明显了', '2016-04-08 00:35:55', '10008');
INSERT INTO `comment` VALUES ('73', '46', '胡歌好帅', '2016-04-08 00:36:51', '10008');
INSERT INTO `comment` VALUES ('74', '49', '好多妹子呀', '2016-04-08 00:38:52', '10004');
INSERT INTO `comment` VALUES ('75', '49', '都在啊…哈哈(ಡωಡ)hiahiahia', '2016-04-08 00:40:40', '10007');
INSERT INTO `comment` VALUES ('77', '16', '空间', '2016-04-09 20:19:37', '10006');
INSERT INTO `comment` VALUES ('78', '16', '咯菌腈', '2016-04-09 20:19:49', '10006');
INSERT INTO `comment` VALUES ('79', '16', '弄', '2016-04-09 20:20:57', '10006');
INSERT INTO `comment` VALUES ('81', '83', '提提姑姑去up', '2016-04-12 19:32:36', '10009');
INSERT INTO `comment` VALUES ('82', '83', '提提姑姑去up', '2016-04-12 19:32:37', '10009');
INSERT INTO `comment` VALUES ('83', '87', '范德萨范德萨十大', '2016-04-14 11:36:43', '10002');
INSERT INTO `comment` VALUES ('84', '90', '赵小龙,小碧池', '2016-04-23 18:04:36', '10011');
INSERT INTO `comment` VALUES ('85', '46', '好帅', '2016-04-29 10:05:08', '10001');
INSERT INTO `comment` VALUES ('86', '114', '6uyyuyui', '2016-04-30 23:49:23', '10001');
INSERT INTO `comment` VALUES ('87', '117', '好美的风景啊……', '2016-05-13 13:05:21', '10002');
INSERT INTO `comment` VALUES ('88', '119', '范德萨安分点是那烦得很机刷卡费范德萨发', '2016-05-14 13:20:27', '10001');
INSERT INTO `comment` VALUES ('91', '83', '看看', '2016-12-15 23:16:05', '10005');

-- ----------------------------
-- Table structure for commoninfo
-- ----------------------------
DROP TABLE IF EXISTS `commoninfo`;
CREATE TABLE `commoninfo` (
  `CommonInfoNo` bigint(20) NOT NULL AUTO_INCREMENT,
  `OffMsgNo` bigint(20) NOT NULL,
  `ToNo` bigint(20) NOT NULL,
  `IsRead` tinyint(1) NOT NULL,
  PRIMARY KEY (`CommonInfoNo`),
  KEY `Refrence_CommonInfo_OffMsg` (`OffMsgNo`),
  CONSTRAINT `Refrence_CommonInfo_OffMsg` FOREIGN KEY (`OffMsgNo`) REFERENCES `offmsg` (`OffMsgNo`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of commoninfo
-- ----------------------------
INSERT INTO `commoninfo` VALUES ('45', '272', '10008', '0');
INSERT INTO `commoninfo` VALUES ('48', '273', '10008', '0');
INSERT INTO `commoninfo` VALUES ('51', '274', '10008', '0');
INSERT INTO `commoninfo` VALUES ('53', '275', '10008', '0');
INSERT INTO `commoninfo` VALUES ('62', '282', '10009', '0');
INSERT INTO `commoninfo` VALUES ('64', '287', '10009', '0');
INSERT INTO `commoninfo` VALUES ('66', '288', '10009', '0');
INSERT INTO `commoninfo` VALUES ('67', '289', '10009', '0');
INSERT INTO `commoninfo` VALUES ('68', '290', '10009', '0');
INSERT INTO `commoninfo` VALUES ('69', '291', '10009', '0');
INSERT INTO `commoninfo` VALUES ('71', '293', '10009', '0');
INSERT INTO `commoninfo` VALUES ('72', '294', '10009', '0');
INSERT INTO `commoninfo` VALUES ('73', '295', '10009', '0');
INSERT INTO `commoninfo` VALUES ('76', '296', '10009', '0');
INSERT INTO `commoninfo` VALUES ('79', '297', '10009', '0');
INSERT INTO `commoninfo` VALUES ('80', '298', '10009', '0');
INSERT INTO `commoninfo` VALUES ('81', '298', '10002', '0');
INSERT INTO `commoninfo` VALUES ('84', '298', '10004', '0');
INSERT INTO `commoninfo` VALUES ('85', '300', '10001', '0');
INSERT INTO `commoninfo` VALUES ('86', '300', '10009', '0');
INSERT INTO `commoninfo` VALUES ('87', '300', '10002', '0');
INSERT INTO `commoninfo` VALUES ('88', '300', '10004', '0');

-- ----------------------------
-- Table structure for contact
-- ----------------------------
DROP TABLE IF EXISTS `contact`;
CREATE TABLE `contact` (
  `ContactNo` bigint(20) NOT NULL AUTO_INCREMENT,
  `UserNo` bigint(20) NOT NULL DEFAULT '0',
  `Mobile` varchar(11) NOT NULL,
  `ContactName` varchar(50) NOT NULL,
  PRIMARY KEY (`ContactNo`),
  KEY `Reference_Contact_User` (`UserNo`),
  CONSTRAINT `Reference_Contact_User` FOREIGN KEY (`UserNo`) REFERENCES `user` (`UserNo`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of contact
-- ----------------------------

-- ----------------------------
-- Table structure for customsession
-- ----------------------------
DROP TABLE IF EXISTS `customsession`;
CREATE TABLE `customsession` (
  `AccessToken` varchar(100) NOT NULL,
  `LastTime` varchar(20) NOT NULL,
  `DeviceType` smallint(6) NOT NULL,
  `UserNo` bigint(20) NOT NULL,
  KEY `Reference_AccessTokens_Users` (`UserNo`),
  CONSTRAINT `Reference_AccessTokens_Users` FOREIGN KEY (`UserNo`) REFERENCES `user` (`UserNo`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customsession
-- ----------------------------
INSERT INTO `customsession` VALUES ('15772463-8316-4615-a6cb-9ff99768c322', '2016-12-15 22:53:34', '1', '10001');
INSERT INTO `customsession` VALUES ('de2d28f5-bc63-4017-b5fd-e54e07575694', '2016-10-17 16:38:59', '1', '10005');
INSERT INTO `customsession` VALUES ('197b5a57-8d97-4dbf-b06e-0e57b98967b1', '2016-10-17 16:08:59', '1', '10002');
INSERT INTO `customsession` VALUES ('d762567c-4b00-4f11-9d61-ceb8a68a65a8', '2016-12-15 23:00:25', '1', '10003');
INSERT INTO `customsession` VALUES ('ee42321c-678b-4281-ad65-6f939f7d43c4', '2016-03-23 19:31:13', '1', '10008');
INSERT INTO `customsession` VALUES ('09dbf78c-0d57-4b99-b809-94a41bc79a7a', '2016-12-15 23:13:28', '2', '10005');
INSERT INTO `customsession` VALUES ('f5281b78-9c09-468a-ac58-b3429e9ad610', '2016-06-17 16:51:24', '2', '10001');
INSERT INTO `customsession` VALUES ('bd4d8f7c-9e76-49d1-9250-25acc782e589', '2016-07-29 16:18:47', '2', '10004');
INSERT INTO `customsession` VALUES ('b402e25d-9509-4fa1-b915-08e49ca96b76', '2016-07-25 12:54:17', '2', '10002');
INSERT INTO `customsession` VALUES ('4f9e668d-e844-4bb3-ab52-f12691c560ca', '2016-07-25 12:58:28', '2', '10003');
INSERT INTO `customsession` VALUES ('68ea7208-3532-47ab-86c3-f4cecf3873fe', '2016-05-06 11:57:08', '1', '10004');
INSERT INTO `customsession` VALUES ('74c9c1e5-e2e3-4ed3-a4c5-3bdf57c1ceb8', '2016-04-01 13:48:39', '1', '10006');
INSERT INTO `customsession` VALUES ('cd27b724-f307-4761-94a4-71a5d8f7d566', '2016-04-01 13:42:12', '1', '10007');
INSERT INTO `customsession` VALUES ('246795d8-faeb-46c9-8d98-314fe08718da', '2016-04-24 22:48:59', '2', '10007');
INSERT INTO `customsession` VALUES ('cf31e052-7e46-44ed-973c-bafec389e9d9', '2016-04-27 13:40:18', '2', '10008');
INSERT INTO `customsession` VALUES ('a3d5dcf5-92ad-455d-8497-4e5ab9e05204', '2016-05-06 11:56:58', '1', '10009');
INSERT INTO `customsession` VALUES ('a81382cd-af21-4e70-9609-241315e44b8b', '2016-05-11 13:22:41', '2', '10006');
INSERT INTO `customsession` VALUES ('b3169a8f-4a96-42b4-95e9-ab875359c1e4', '2016-04-28 00:37:07', '2', '10009');
INSERT INTO `customsession` VALUES ('f77219a2-cbf5-41a3-88d9-f5001af3ca80', '2016-05-06 12:56:30', '2', '10010');
INSERT INTO `customsession` VALUES ('b57f8dba-100b-4693-aa4d-6e26daef39a5', '2016-04-24 23:02:04', '2', '10011');
INSERT INTO `customsession` VALUES ('516ed32b-ca79-4f34-ae3c-4b6b9e6298a1', '2016-12-15 23:12:48', '2', '10012');

-- ----------------------------
-- Table structure for feedback
-- ----------------------------
DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback` (
  `feBaNo` bigint(20) NOT NULL AUTO_INCREMENT,
  `userNo` bigint(20) NOT NULL,
  `content` varchar(500) NOT NULL,
  `time` varchar(20) NOT NULL,
  PRIMARY KEY (`feBaNo`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of feedback
-- ----------------------------
INSERT INTO `feedback` VALUES ('1', '10001', 'MSN公公婆婆QQ亲破LOL哦哦咯咯LOL墨迹', '2016-04-06 23:00:35');
INSERT INTO `feedback` VALUES ('2', '10001', '民工您名模咯咯OK咯咯明哦里季姬击鸡记明哦LOL哦哦咯LOL呢个里立体图回来了别饿呢饿鹅鹅鹅鹅鹅鹅', '2016-04-06 23:02:12');
INSERT INTO `feedback` VALUES ('3', '10001', '急急急5级利济路呼呼呼体会脱衣舞妮子嘻嘻YY街子清真寺星期五下午啥时候咯红红火火后哦7名9亿一直以来异界之九阳真经重口味心理学可以可以休息休息嘻嘻嘻嘻嘻嘻嘻9亿鱼我所欲也水至清则无鱼啥子意思五一一心一意重口味心理学西游记续集我咯了OK娱乐无极限无语越来越无语重口味心理学可以咯来咯咯哦哦OK重口味心理学急性阑尾炎咯娄哦哦来咯咯哦哦哦咯了越来越加油加油异界之九阳真经我咯哦哦咯哦咯咯空间咯小鲤鱼历险记谢', '2016-04-06 23:02:54');
INSERT INTO `feedback` VALUES ('4', '10001', '明MSN磨破您( *✪㉨✪)✄╰ひ╯（▼㉨▼メ）', '2016-04-08 00:47:25');
INSERT INTO `feedback` VALUES ('5', '10001', 'Ψ（◣㉨◢）（▼㉨▼メ）φ(.㉨.;)写作业⁄(⁄ ⁄•⁄㉨⁄•⁄ ⁄)⁄ ⁄(⁄ ⁄•⁄㉨⁄•⁄ ⁄)⁄ ⁄(⁄ ⁄•⁄㉨⁄•⁄ ⁄)⁄ ⁄(⁄ ⁄•⁄㉨⁄•⁄ ⁄)⁄ φ(.㉨.;)写作业⁄(⁄ ⁄•⁄㉨⁄•⁄ ⁄)⁄ （▼㉨▼メ）҉٩(*^㉨^*)۶( ⁼̴̤̆ ㉨⁼̴̤̆  )（▼㉨▼メ）ヽ(○^㉨^)ﾉ♪⁄(⁄ ⁄•⁄㉨⁄•⁄ ⁄)⁄ ๑乛㉨乛๑҉٩(*^㉨^*)۶҉٩(*^㉨^*)۶(', '2016-04-08 00:48:13');
INSERT INTO `feedback` VALUES ('6', '10004', 'だいたい', '2016-07-29 16:19:44');

-- ----------------------------
-- Table structure for friend
-- ----------------------------
DROP TABLE IF EXISTS `friend`;
CREATE TABLE `friend` (
  `FriendNo` bigint(20) NOT NULL AUTO_INCREMENT,
  `UserANo` bigint(20) DEFAULT NULL,
  `UserBNo` bigint(20) NOT NULL,
  `Remark` varchar(100) NOT NULL,
  `StartTime` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`FriendNo`),
  KEY `Reference_Friend_User` (`UserANo`),
  CONSTRAINT `Reference_Friend_User` FOREIGN KEY (`UserANo`) REFERENCES `user` (`UserNo`)
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of friend
-- ----------------------------
INSERT INTO `friend` VALUES ('3', '10001', '10004', '刘备', '2015-12-10 09:10');
INSERT INTO `friend` VALUES ('6', '10004', '10001', '曹操', '2015-12-10 09:10');
INSERT INTO `friend` VALUES ('11', '10001', '10007', '1赵子龙', '2015-12-10 09:12:20');
INSERT INTO `friend` VALUES ('12', '10007', '10001', '曹贼', '2015-12-10 09:12:20');
INSERT INTO `friend` VALUES ('15', '10001', '10009', '吕布', '2015-12-10 09:12:20');
INSERT INTO `friend` VALUES ('16', '10009', '10001', '曹丞相', '2015-12-10 09:12:20');
INSERT INTO `friend` VALUES ('25', '10004', '10003', '诸葛孔明', '2016-04-24 22:56:03');
INSERT INTO `friend` VALUES ('26', '10003', '10004', '刘玄德', '2016-04-24 22:56:03');
INSERT INTO `friend` VALUES ('27', '10005', '10003', '诸葛孔明', '2016-04-24 22:58:18');
INSERT INTO `friend` VALUES ('28', '10003', '10005', '关云长', '2016-04-24 22:58:18');
INSERT INTO `friend` VALUES ('29', '10003', '10011', '周瑜', '2016-04-24 22:59:35');
INSERT INTO `friend` VALUES ('30', '10011', '10003', '诸葛孔明', '2016-04-24 22:59:35');
INSERT INTO `friend` VALUES ('31', '10003', '10006', '貂蝉', '2016-04-24 23:02:49');
INSERT INTO `friend` VALUES ('32', '10006', '10003', '诸葛孔明', '2016-04-24 23:02:49');
INSERT INTO `friend` VALUES ('45', '10001', '10002', '张翼德', '2016-04-25 15:26:20');
INSERT INTO `friend` VALUES ('46', '10002', '10001', '曹小宝', '2016-04-25 15:26:20');
INSERT INTO `friend` VALUES ('49', '10003', '10002', '张翼德', '2016-04-25 15:37:53');
INSERT INTO `friend` VALUES ('50', '10002', '10003', '亮亮', '2016-04-25 15:37:53');
INSERT INTO `friend` VALUES ('51', '10004', '10002', '张翼德', '2016-04-25 15:39:14');
INSERT INTO `friend` VALUES ('52', '10002', '10004', '刘玄德', '2016-04-25 15:39:14');
INSERT INTO `friend` VALUES ('55', '10002', '10006', '貂蝉', '2016-04-25 23:43:03');
INSERT INTO `friend` VALUES ('56', '10006', '10002', '张翼德', '2016-04-25 23:43:03');
INSERT INTO `friend` VALUES ('57', '10002', '10008', '周公瑾', '2016-04-26 13:15:32');
INSERT INTO `friend` VALUES ('58', '10008', '10002', '张翼德', '2016-04-26 13:15:32');
INSERT INTO `friend` VALUES ('59', '10004', '10006', '貂蝉', '2016-04-26 13:45:08');
INSERT INTO `friend` VALUES ('60', '10006', '10004', '刘玄德', '2016-04-26 13:45:08');
INSERT INTO `friend` VALUES ('63', '10001', '10008', '周公瑾', '2016-04-27 13:31:51');
INSERT INTO `friend` VALUES ('64', '10008', '10001', '小郭子啦', '2016-04-27 13:31:51');
INSERT INTO `friend` VALUES ('65', '10005', '10008', '周公瑾', '2016-04-27 13:34:35');
INSERT INTO `friend` VALUES ('66', '10008', '10005', '关云长', '2016-04-27 13:34:35');
INSERT INTO `friend` VALUES ('77', '10003', '10001', '小郭子啦', '2016-04-28 15:04:00');
INSERT INTO `friend` VALUES ('78', '10001', '10003', '诸葛孔明', '2016-04-28 15:04:00');
INSERT INTO `friend` VALUES ('79', '10006', '10005', '关云长', '2016-05-11 13:22:17');
INSERT INTO `friend` VALUES ('80', '10005', '10006', '貂蝉', '2016-05-11 13:22:17');
INSERT INTO `friend` VALUES ('81', '10012', '10005', '关云长', '2016-12-15 23:12:29');
INSERT INTO `friend` VALUES ('82', '10005', '10012', '1481810908090', '2016-12-15 23:12:29');

-- ----------------------------
-- Table structure for good
-- ----------------------------
DROP TABLE IF EXISTS `good`;
CREATE TABLE `good` (
  `GoodNo` bigint(20) NOT NULL AUTO_INCREMENT,
  `RecallNo` bigint(20) NOT NULL,
  `UserNo` bigint(20) NOT NULL,
  `GoodTime` varchar(20) NOT NULL,
  PRIMARY KEY (`GoodNo`),
  KEY `Reference_Good_Recall` (`RecallNo`),
  CONSTRAINT `Reference_Good_Recall` FOREIGN KEY (`RecallNo`) REFERENCES `recall` (`RecallNo`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=178 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of good
-- ----------------------------
INSERT INTO `good` VALUES ('4', '2', '10003', '2015-12-20 20:12:12');
INSERT INTO `good` VALUES ('5', '2', '10005', '2015-12-20 20:12:12');
INSERT INTO `good` VALUES ('14', '10', '10006', '2015-12-25 20:43:21');
INSERT INTO `good` VALUES ('18', '8', '10001', '2015-12-25 22:58:30');
INSERT INTO `good` VALUES ('22', '11', '10001', '2015-12-26 09:48:23');
INSERT INTO `good` VALUES ('29', '2', '10002', '2015-12-27 14:35:30');
INSERT INTO `good` VALUES ('38', '18', '10002', '2016-01-11 16:33:53');
INSERT INTO `good` VALUES ('46', '16', '10001', '2016-01-14 19:34:21');
INSERT INTO `good` VALUES ('49', '10', '10001', '2016-01-19 21:30:09');
INSERT INTO `good` VALUES ('50', '18', '10001', '2016-01-24 13:24:07');
INSERT INTO `good` VALUES ('51', '39', '10002', '2016-03-23 19:10:17');
INSERT INTO `good` VALUES ('52', '39', '10003', '2016-03-23 19:30:14');
INSERT INTO `good` VALUES ('53', '39', '10004', '2016-03-23 19:30:27');
INSERT INTO `good` VALUES ('54', '39', '10005', '2016-03-23 19:30:38');
INSERT INTO `good` VALUES ('55', '39', '10006', '2016-03-23 19:30:51');
INSERT INTO `good` VALUES ('56', '39', '10007', '2016-03-23 19:31:03');
INSERT INTO `good` VALUES ('57', '39', '10008', '2016-03-23 19:31:15');
INSERT INTO `good` VALUES ('69', '2', '10001', '2016-03-25 21:08:10');
INSERT INTO `good` VALUES ('70', '40', '10004', '2016-03-26 09:38:04');
INSERT INTO `good` VALUES ('71', '40', '10002', '2016-03-26 09:38:53');
INSERT INTO `good` VALUES ('72', '40', '10005', '2016-03-26 09:41:49');
INSERT INTO `good` VALUES ('73', '41', '10006', '2016-03-26 17:55:16');
INSERT INTO `good` VALUES ('76', '41', '10008', '2016-03-31 23:03:34');
INSERT INTO `good` VALUES ('78', '40', '10001', '2016-04-01 12:02:02');
INSERT INTO `good` VALUES ('83', '42', '10001', '2016-04-03 16:43:29');
INSERT INTO `good` VALUES ('86', '48', '10001', '2016-04-05 18:11:55');
INSERT INTO `good` VALUES ('87', '40', '10003', '2016-04-05 19:15:37');
INSERT INTO `good` VALUES ('88', '47', '10001', '2016-04-06 10:42:08');
INSERT INTO `good` VALUES ('90', '50', '10003', '2016-04-07 22:06:52');
INSERT INTO `good` VALUES ('92', '49', '10006', '2016-04-08 00:31:14');
INSERT INTO `good` VALUES ('93', '48', '10006', '2016-04-08 00:34:38');
INSERT INTO `good` VALUES ('94', '49', '10008', '2016-04-08 00:36:32');
INSERT INTO `good` VALUES ('95', '47', '10008', '2016-04-08 00:37:24');
INSERT INTO `good` VALUES ('96', '49', '10004', '2016-04-08 00:38:38');
INSERT INTO `good` VALUES ('97', '49', '10007', '2016-04-08 00:40:20');
INSERT INTO `good` VALUES ('98', '49', '10003', '2016-04-08 00:52:07');
INSERT INTO `good` VALUES ('99', '49', '10002', '2016-04-08 00:53:16');
INSERT INTO `good` VALUES ('100', '49', '10005', '2016-04-08 00:53:46');
INSERT INTO `good` VALUES ('101', '50', '10005', '2016-04-08 00:55:15');
INSERT INTO `good` VALUES ('102', '48', '10005', '2016-04-08 00:55:20');
INSERT INTO `good` VALUES ('103', '50', '10006', '2016-04-09 20:31:21');
INSERT INTO `good` VALUES ('106', '50', '10001', '2016-04-10 10:40:11');
INSERT INTO `good` VALUES ('112', '83', '10009', '2016-04-12 19:32:31');
INSERT INTO `good` VALUES ('113', '84', '10009', '2016-04-12 19:36:06');
INSERT INTO `good` VALUES ('114', '89', '10001', '2016-04-14 15:43:13');
INSERT INTO `good` VALUES ('116', '92', '10001', '2016-04-16 08:27:15');
INSERT INTO `good` VALUES ('117', '93', '10001', '2016-04-16 08:33:48');
INSERT INTO `good` VALUES ('118', '93', '10003', '2016-04-18 18:00:49');
INSERT INTO `good` VALUES ('120', '16', '10003', '2016-04-18 18:02:15');
INSERT INTO `good` VALUES ('138', '110', '10001', '2016-04-18 21:12:25');
INSERT INTO `good` VALUES ('156', '111', '10002', '2016-04-23 17:51:49');
INSERT INTO `good` VALUES ('159', '112', '10001', '2016-04-23 18:17:53');
INSERT INTO `good` VALUES ('160', '110', '10011', '2016-04-23 18:18:08');
INSERT INTO `good` VALUES ('161', '112', '10011', '2016-04-23 18:26:39');
INSERT INTO `good` VALUES ('162', '87', '10002', '2016-04-24 23:54:42');
INSERT INTO `good` VALUES ('163', '83', '10002', '2016-04-24 23:55:02');
INSERT INTO `good` VALUES ('164', '76', '10002', '2016-04-24 23:55:07');
INSERT INTO `good` VALUES ('165', '87', '10001', '2016-04-27 10:34:21');
INSERT INTO `good` VALUES ('166', '46', '10001', '2016-04-29 10:04:56');
INSERT INTO `good` VALUES ('167', '114', '10001', '2016-04-30 23:49:17');
INSERT INTO `good` VALUES ('168', '114', '10002', '2016-04-30 23:54:18');
INSERT INTO `good` VALUES ('169', '117', '10002', '2016-05-13 13:04:49');
INSERT INTO `good` VALUES ('172', '120', '10003', '2016-07-25 12:56:57');
INSERT INTO `good` VALUES ('173', '121', '10001', '2016-07-25 12:57:23');
INSERT INTO `good` VALUES ('174', '119', '10001', '2016-07-29 16:43:13');
INSERT INTO `good` VALUES ('175', '119', '10005', '2016-12-15 23:14:08');
INSERT INTO `good` VALUES ('176', '83', '10005', '2016-12-15 23:15:18');
INSERT INTO `good` VALUES ('177', '123', '10003', '2016-12-15 23:22:04');

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `MemberNo` bigint(20) NOT NULL AUTO_INCREMENT,
  `PartyNo` bigint(20) DEFAULT NULL,
  `UserNo` bigint(20) DEFAULT NULL,
  `AddTime` varchar(20) NOT NULL,
  `MemberName` varchar(100) DEFAULT NULL,
  `Type` int(11) DEFAULT NULL,
  PRIMARY KEY (`MemberNo`),
  KEY `Reference_Member_Party` (`PartyNo`),
  CONSTRAINT `Reference_Member_Party` FOREIGN KEY (`PartyNo`) REFERENCES `party` (`PartyNo`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=115 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of member
-- ----------------------------
INSERT INTO `member` VALUES ('1', '1', '10001', '2015-12-12 12:20:20', '我是曹宝宝', '1');
INSERT INTO `member` VALUES ('4', '2', '10002', '2016-04-25 19:34:18', '张翼德', '1');
INSERT INTO `member` VALUES ('5', '3', '10002', '2016-04-25 19:38:01', '张翼德', '1');
INSERT INTO `member` VALUES ('6', '2', '10004', '2016-04-26 12:39:15', '刘玄德', '2');
INSERT INTO `member` VALUES ('7', '2', '10006', '2016-04-26 12:55:38', '貂蝉', '2');
INSERT INTO `member` VALUES ('8', '3', '10006', '2016-04-26 13:09:14', '貂蝉', '2');
INSERT INTO `member` VALUES ('9', '2', '10008', '2016-04-26 13:16:55', '周俞', '2');
INSERT INTO `member` VALUES ('10', '3', '10008', '2016-04-26 13:17:12', '周公瑾', '2');
INSERT INTO `member` VALUES ('28', '2', '10001', '2016-04-26 18:43:15', '小郭子啦', '2');
INSERT INTO `member` VALUES ('102', '1', '10009', '2016-04-28 00:19:05', '吕奉先', '2');
INSERT INTO `member` VALUES ('103', '1', '10002', '2016-04-28 00:19:05', '张翼德', '2');
INSERT INTO `member` VALUES ('104', '1', '10003', '2016-04-28 00:19:05', '诸葛孔明', '2');
INSERT INTO `member` VALUES ('105', '5', '10001', '2016-04-28 12:18:06', '小郭子啦', '1');
INSERT INTO `member` VALUES ('107', '5', '10002', '2016-04-28 12:20:09', '张翼德', '2');
INSERT INTO `member` VALUES ('110', '5', '10003', '2016-04-28 15:20:01', '诸葛孔明', '2');
INSERT INTO `member` VALUES ('111', '5', '10005', '2016-04-28 20:30:49', '关云长', '2');
INSERT INTO `member` VALUES ('112', '5', '10006', '2016-04-29 10:06:19', '貂蝉', '2');
INSERT INTO `member` VALUES ('113', '1', '10005', '2016-07-25 12:58:07', '关云长', '2');
INSERT INTO `member` VALUES ('114', '1', '10004', '2016-07-25 12:58:07', '刘玄德', '2');

-- ----------------------------
-- Table structure for offmsg
-- ----------------------------
DROP TABLE IF EXISTS `offmsg`;
CREATE TABLE `offmsg` (
  `OffMsgNo` bigint(20) NOT NULL AUTO_INCREMENT,
  `FromNo` bigint(20) NOT NULL,
  `ToNo` bigint(20) NOT NULL,
  `Content` varchar(2000) NOT NULL,
  `Time` varchar(20) DEFAULT NULL,
  `IsRead` tinyint(1) DEFAULT '0',
  `MsgType` smallint(6) NOT NULL,
  `ConType` smallint(6) NOT NULL,
  PRIMARY KEY (`OffMsgNo`)
) ENGINE=InnoDB AUTO_INCREMENT=301 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of offmsg
-- ----------------------------
INSERT INTO `offmsg` VALUES ('253', '10003', '5', 'ffff', '2016-04-29 10:00:26', '0', '5', '1');
INSERT INTO `offmsg` VALUES ('254', '10003', '5', 'ssss', '2016-04-29 10:00:29', '0', '5', '1');
INSERT INTO `offmsg` VALUES ('255', '10003', '5', '4343243', '2016-04-29 10:00:30', '0', '5', '1');
INSERT INTO `offmsg` VALUES ('256', '10003', '5', '43242432', '2016-04-29 10:00:32', '0', '5', '1');
INSERT INTO `offmsg` VALUES ('257', '10003', '5', 'fdsfdsas', '2016-04-29 10:00:33', '0', '5', '1');
INSERT INTO `offmsg` VALUES ('258', '10003', '5', 'dsafdsaf', '2016-04-29 10:00:34', '0', '5', '1');
INSERT INTO `offmsg` VALUES ('259', '10003', '1', '432432', '2016-04-29 10:00:42', '0', '5', '1');
INSERT INTO `offmsg` VALUES ('260', '10003', '1', '432432', '2016-04-29 10:00:43', '0', '5', '1');
INSERT INTO `offmsg` VALUES ('261', '10003', '1', '432432', '2016-04-29 10:00:44', '0', '5', '1');
INSERT INTO `offmsg` VALUES ('262', '10003', '1', '43243', '2016-04-29 10:00:46', '0', '5', '1');
INSERT INTO `offmsg` VALUES ('263', '10003', '1', '432432', '2016-04-29 10:00:47', '0', '5', '1');
INSERT INTO `offmsg` VALUES ('264', '10003', '1', '654654', '2016-04-29 10:00:49', '0', '5', '1');
INSERT INTO `offmsg` VALUES ('265', '10003', '1', '543', '2016-04-29 10:00:50', '0', '5', '1');
INSERT INTO `offmsg` VALUES ('266', '10003', '1', '42', '2016-04-29 10:00:51', '0', '5', '1');
INSERT INTO `offmsg` VALUES ('267', '10006', '5', '', '2016-04-29 10:06:19', '0', '19', '1');
INSERT INTO `offmsg` VALUES ('268', '10003', '5', '民工您', '2016-04-30 23:47:09', '0', '5', '1');
INSERT INTO `offmsg` VALUES ('269', '10001', '5', 'hjh', '2016-04-30 23:47:20', '0', '5', '1');
INSERT INTO `offmsg` VALUES ('270', '10002', '5', 'MSN婆婆哦QQ', '2016-05-03 00:02:05', '0', '5', '1');
INSERT INTO `offmsg` VALUES ('271', '10001', '5', '健康快乐', '2016-05-03 00:02:08', '0', '5', '1');
INSERT INTO `offmsg` VALUES ('272', '10000', '3', 'images/head/2016/05/03/1462205277502.jpg', '2016-05-03 00:07:57', '0', '9', '1');
INSERT INTO `offmsg` VALUES ('273', '10000', '2', 'images/head/2016/05/03/1462205422499.jpg', '2016-05-03 00:10:22', '0', '9', '1');
INSERT INTO `offmsg` VALUES ('274', '10000', '2', 'images/head/2016/05/03/1462205436708.jpg', '2016-05-03 00:10:36', '0', '9', '1');
INSERT INTO `offmsg` VALUES ('275', '10000', '3', 'images/head/2016/05/03/1462205475226.jpg', '2016-05-03 00:11:15', '0', '9', '1');
INSERT INTO `offmsg` VALUES ('276', '10002', '5', '因为我', '2016-05-03 00:12:39', '0', '5', '1');
INSERT INTO `offmsg` VALUES ('277', '10005', '5', '大家好', '2016-05-06 12:42:04', '0', '5', '1');
INSERT INTO `offmsg` VALUES ('282', '10003', '1', '嘿嘿', '2016-05-06 12:57:30', '0', '5', '1');
INSERT INTO `offmsg` VALUES ('284', '10001', '10009', '你好啊', '2016-05-06 12:58:08', '0', '2', '1');
INSERT INTO `offmsg` VALUES ('285', '10001', '5', '一曲相送你在', '2016-05-06 13:01:59', '0', '5', '1');
INSERT INTO `offmsg` VALUES ('286', '10001', '5', '命悬一线', '2016-05-06 13:02:01', '0', '5', '1');
INSERT INTO `offmsg` VALUES ('287', '10002', '1', '立体图去上自习', '2016-05-14 13:06:33', '0', '5', '1');
INSERT INTO `offmsg` VALUES ('288', '10003', '1', 'kl', '2016-05-14 13:06:44', '0', '5', '1');
INSERT INTO `offmsg` VALUES ('289', '10001', '1', '尽快尽快', '2016-05-14 13:07:17', '0', '5', '1');
INSERT INTO `offmsg` VALUES ('290', '10003', '1', 'jkjl', '2016-05-14 13:07:48', '0', '5', '1');
INSERT INTO `offmsg` VALUES ('291', '10002', '1', '啊啊&nbsp;', '2016-05-14 13:21:22', '0', '5', '1');
INSERT INTO `offmsg` VALUES ('293', '10002', '1', '范德萨发', '2016-07-25 12:56:28', '0', '5', '1');
INSERT INTO `offmsg` VALUES ('294', '10003', '1', '你明强迫', '2016-07-25 12:56:36', '0', '5', '1');
INSERT INTO `offmsg` VALUES ('295', '10003', '1', '{\"contNo\":1,\"displayName\":\"一群逗比\",\"gender\":0,\"information\":\"这是丢丢童年社交软件的第一个群……具有特别的意义哦\",\"memberList\":[{\"addTime\":\"2016-07-25 12:58:07\",\"memberName\":\"关云长\",\"memberNo\":113,\"partyNo\":1,\"picPath\":\"images/head/2015/12/15/10005.png\",\"type\":2,\"userNo\":10005},{\"addTime\":\"2016-07-25 12:58:07\",\"memberName\":\"刘玄德\",\"memberNo\":114,\"partyNo\":1,\"picPath\":\"images/head/2015/12/14/10004.png\",\"type\":2,\"userNo\":10004}],\"ownerNo\":10001,\"picPath\":\"images/head/2016/04/23/1461379005434.jpg\",\"startTime\":\"2015-12-12 20:12:20\",\"type\":1}', '2016-07-25 12:58:07', '0', '20', '1');
INSERT INTO `offmsg` VALUES ('296', '10004', '1', '我人在全心全意', '2016-07-25 12:58:45', '0', '5', '1');
INSERT INTO `offmsg` VALUES ('297', '10004', '1', 'jdbxbxb', '2016-07-29 16:42:05', '0', '5', '1');
INSERT INTO `offmsg` VALUES ('298', '10001', '1', '太热问题&nbsp;', '2016-12-15 22:52:45', '0', '5', '1');
INSERT INTO `offmsg` VALUES ('300', '10003', '1', '&nbsp;房顶上撒范德萨', '2016-12-15 23:01:56', '0', '5', '1');

-- ----------------------------
-- Table structure for party
-- ----------------------------
DROP TABLE IF EXISTS `party`;
CREATE TABLE `party` (
  `PartyNo` bigint(20) NOT NULL AUTO_INCREMENT,
  `UserNo` bigint(20) DEFAULT NULL,
  `PartyName` varchar(100) NOT NULL,
  `Information` varchar(200) DEFAULT NULL,
  `RegistTime` varchar(20) DEFAULT NULL,
  `PicPath` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`PartyNo`),
  KEY `Reference_Party_User` (`UserNo`),
  CONSTRAINT `Reference_Party_User` FOREIGN KEY (`UserNo`) REFERENCES `user` (`UserNo`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of party
-- ----------------------------
INSERT INTO `party` VALUES ('1', '10001', '一群逗比', '这是丢丢童年社交软件的第一个群……具有特别的意义哦', '2015-12-12 20:12:20', 'images/head/2016/04/23/1461379005434.jpg');
INSERT INTO `party` VALUES ('2', '10002', '开发交流群', '大家好……欢迎分享开发经验', '2016-04-25 19:34:17', 'images/head/2016/05/03/1462205436708.jpg');
INSERT INTO `party` VALUES ('3', '10002', '看看我们的曾经', null, '2016-04-25 19:38:01', 'images/head/2016/05/03/1462205475226.jpg');
INSERT INTO `party` VALUES ('5', '10001', '胡歌粉丝群', null, '2016-04-28 12:18:06', 'images/head/2016/04/28/1461817086450.jpg');

-- ----------------------------
-- Table structure for picture
-- ----------------------------
DROP TABLE IF EXISTS `picture`;
CREATE TABLE `picture` (
  `PictureNo` bigint(20) NOT NULL AUTO_INCREMENT,
  `RecallNo` bigint(20) NOT NULL,
  `PicPath` varchar(100) NOT NULL,
  `Height` int(11) NOT NULL,
  `Width` int(11) NOT NULL,
  `offSetTop` int(11) NOT NULL,
  `offSetLeft` int(11) NOT NULL,
  PRIMARY KEY (`PictureNo`),
  KEY `Reference_Picture_Recall` (`RecallNo`),
  CONSTRAINT `Reference_Picture_Recall` FOREIGN KEY (`RecallNo`) REFERENCES `recall` (`RecallNo`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=295 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of picture
-- ----------------------------
INSERT INTO `picture` VALUES ('5', '2', 'images/album/2015/12/20/5.png', '0', '176', '-75', '0');
INSERT INTO `picture` VALUES ('6', '2', 'images/album/2015/12/20/6.png', '0', '176', '-1', '0');
INSERT INTO `picture` VALUES ('7', '2', 'images/album/2015/12/20/1.png', '114', '0', '0', '-28');
INSERT INTO `picture` VALUES ('8', '2', 'images/album/2015/12/20/2.png', '0', '176', '-1', '0');
INSERT INTO `picture` VALUES ('9', '2', 'images/album/2015/12/20/4.png', '0', '176', '-68', '0');
INSERT INTO `picture` VALUES ('22', '33', 'images/album/2016/01/20/1453288415603.jpg', '0', '176', '-9', '0');
INSERT INTO `picture` VALUES ('23', '33', 'images/album/2016/01/20/1453288413737.jpg', '0', '176', '-9', '0');
INSERT INTO `picture` VALUES ('24', '33', 'images/album/2016/01/20/1453288412050.jpg', '0', '176', '-9', '0');
INSERT INTO `picture` VALUES ('25', '33', 'images/album/2016/01/20/1453288410101.jpg', '0', '176', '-9', '0');
INSERT INTO `picture` VALUES ('26', '33', 'images/album/2016/01/20/1453288426137.jpg', '0', '176', '-9', '0');
INSERT INTO `picture` VALUES ('27', '33', 'images/album/2016/01/20/1453288417580.jpg', '0', '176', '-9', '0');
INSERT INTO `picture` VALUES ('34', '36', 'images/album/2016/01/24/1453612935721.jpg', '0', '176', '-9', '0');
INSERT INTO `picture` VALUES ('35', '36', 'images/album/2016/01/24/1453612933361.jpg', '0', '176', '-9', '0');
INSERT INTO `picture` VALUES ('38', '39', 'images/album/2016/03/23/1458721498917.jpg', '0', '176', '-9', '0');
INSERT INTO `picture` VALUES ('39', '39', 'images/album/2016/03/23/1458721516559.jpg', '0', '176', '-13', '0');
INSERT INTO `picture` VALUES ('40', '40', 'images/album/2016/03/26/1458956275200.jpg', '114', '0', '0', '-12');
INSERT INTO `picture` VALUES ('41', '41', 'images/album/2016/03/26/1458986058142.jpg', '0', '176', '-9', '0');
INSERT INTO `picture` VALUES ('42', '41', 'images/album/2016/03/26/1458986066283.jpg', '0', '176', '-9', '0');
INSERT INTO `picture` VALUES ('43', '41', 'images/album/2016/03/26/1458986068578.jpg', '0', '176', '-9', '0');
INSERT INTO `picture` VALUES ('44', '41', 'images/album/2016/03/26/1458986070970.jpg', '0', '176', '-9', '0');
INSERT INTO `picture` VALUES ('45', '41', 'images/album/2016/03/26/1458986073762.jpg', '0', '176', '-9', '0');
INSERT INTO `picture` VALUES ('46', '41', 'images/album/2016/03/26/1458986076096.jpg', '0', '176', '-9', '0');
INSERT INTO `picture` VALUES ('47', '41', 'images/album/2016/03/26/1458986078340.jpg', '0', '176', '-9', '0');
INSERT INTO `picture` VALUES ('48', '41', 'images/album/2016/03/26/1458986083704.jpg', '0', '176', '-9', '0');
INSERT INTO `picture` VALUES ('49', '41', 'images/album/2016/03/26/1458986090774.jpg', '0', '176', '-9', '0');
INSERT INTO `picture` VALUES ('50', '43', 'images/album/2016/04/01/1459489302018.jpg', '0', '176', '-8', '0');
INSERT INTO `picture` VALUES ('51', '43', 'images/album/2016/04/01/1459489305819.jpeg', '0', '176', '-8', '0');
INSERT INTO `picture` VALUES ('52', '43', 'images/album/2016/04/01/1459489309833.jpg', '0', '176', '-9', '0');
INSERT INTO `picture` VALUES ('53', '43', 'images/album/2016/04/01/1459489312640.jpg', '0', '176', '-9', '0');
INSERT INTO `picture` VALUES ('54', '43', 'images/album/2016/04/01/1459489315321.jpg', '0', '176', '-9', '0');
INSERT INTO `picture` VALUES ('55', '44', 'images/album/2016/04/01/1459489472436.jpg', '0', '176', '-75', '0');
INSERT INTO `picture` VALUES ('56', '44', 'images/album/2016/04/01/1459489476898.jpg', '0', '176', '-31', '0');
INSERT INTO `picture` VALUES ('57', '44', 'images/album/2016/04/01/1459489480947.jpg', '0', '176', '-1', '0');
INSERT INTO `picture` VALUES ('58', '44', 'images/album/2016/04/01/1459489487160.jpg', '0', '176', '-75', '0');
INSERT INTO `picture` VALUES ('59', '44', 'images/album/2016/04/01/1459489491878.jpg', '0', '176', '-31', '0');
INSERT INTO `picture` VALUES ('60', '46', 'images/album/2016/04/01/1459489735143.jpg', '0', '176', '-75', '0');
INSERT INTO `picture` VALUES ('61', '46', 'images/album/2016/04/01/1459489739632.jpg', '0', '176', '-9', '0');
INSERT INTO `picture` VALUES ('62', '46', 'images/album/2016/04/01/1459489743809.jpeg', '0', '176', '-1', '0');
INSERT INTO `picture` VALUES ('63', '46', 'images/album/2016/04/01/1459489750326.jpeg', '0', '176', '-1', '0');
INSERT INTO `picture` VALUES ('64', '47', 'images/album/2016/04/01/1459489774183.jpeg', '0', '176', '-74', '0');
INSERT INTO `picture` VALUES ('65', '47', 'images/album/2016/04/01/1459489778890.jpg', '0', '176', '-1', '0');
INSERT INTO `picture` VALUES ('66', '47', 'images/album/2016/04/01/1459489791989.jpg', '0', '176', '-1', '0');
INSERT INTO `picture` VALUES ('67', '48', 'images/album/2016/04/05/1459850338950.jpg', '0', '176', '-43', '0');
INSERT INTO `picture` VALUES ('68', '49', 'images/album/2016/04/05/1459856536731.jpg', '0', '176', '-9', '0');
INSERT INTO `picture` VALUES ('69', '49', 'images/album/2016/04/05/1459856543406.JPG', '0', '176', '-1', '0');
INSERT INTO `picture` VALUES ('70', '49', 'images/album/2016/04/05/1459856546198.JPG', '0', '176', '-60', '0');
INSERT INTO `picture` VALUES ('71', '50', 'images/album/2016/04/05/1459857553537.jpg', '114', '0', '0', '-13');
INSERT INTO `picture` VALUES ('144', '72', 'images/album/2016/04/12/1460457296047.png', '0', '176', '-99', '0');
INSERT INTO `picture` VALUES ('145', '72', 'images/album/2016/04/12/1460457296529.png', '0', '176', '-99', '0');
INSERT INTO `picture` VALUES ('146', '72', 'images/album/2016/04/12/1460457296835.png', '0', '176', '-99', '0');
INSERT INTO `picture` VALUES ('147', '72', 'images/album/2016/04/12/1460457297133.png', '0', '176', '-99', '0');
INSERT INTO `picture` VALUES ('148', '72', 'images/album/2016/04/12/1460457297404.png', '0', '176', '-99', '0');
INSERT INTO `picture` VALUES ('149', '72', 'images/album/2016/04/12/1460457297692.png', '0', '176', '-99', '0');
INSERT INTO `picture` VALUES ('150', '72', 'images/album/2016/04/12/1460457298170.png', '0', '176', '-99', '0');
INSERT INTO `picture` VALUES ('151', '73', 'images/album/2016/04/12/1460457349517.jpg', '0', '176', '-31', '0');
INSERT INTO `picture` VALUES ('152', '73', 'images/album/2016/04/12/1460457349905.jpg', '0', '176', '-31', '0');
INSERT INTO `picture` VALUES ('153', '74', 'images/album/2016/04/12/1460457386196.jpg', '0', '176', '-31', '0');
INSERT INTO `picture` VALUES ('154', '74', 'images/album/2016/04/12/1460457386570.jpg', '0', '176', '-31', '0');
INSERT INTO `picture` VALUES ('155', '74', 'images/album/2016/04/12/1460457386890.jpg', '0', '176', '-31', '0');
INSERT INTO `picture` VALUES ('156', '74', 'images/album/2016/04/12/1460457387959.jpg', '0', '176', '-60', '0');
INSERT INTO `picture` VALUES ('157', '74', 'images/album/2016/04/12/1460457389858.jpg', '114', '0', '0', '-13');
INSERT INTO `picture` VALUES ('158', '74', 'images/album/2016/04/12/1460457390881.png', '0', '176', '-99', '0');
INSERT INTO `picture` VALUES ('159', '74', 'images/album/2016/04/12/1460457392357.jpg', '0', '176', '-60', '0');
INSERT INTO `picture` VALUES ('160', '74', 'images/album/2016/04/12/1460457394766.png', '0', '176', '-99', '0');
INSERT INTO `picture` VALUES ('161', '75', 'images/album/2016/04/12/1460457447260.jpg', '0', '176', '-60', '0');
INSERT INTO `picture` VALUES ('162', '75', 'images/album/2016/04/12/1460457448984.jpg', '0', '176', '-60', '0');
INSERT INTO `picture` VALUES ('163', '75', 'images/album/2016/04/12/1460457451681.jpg', '0', '176', '-9', '0');
INSERT INTO `picture` VALUES ('164', '75', 'images/album/2016/04/12/1460457453648.png', '0', '176', '-99', '0');
INSERT INTO `picture` VALUES ('165', '75', 'images/album/2016/04/12/1460457454221.png', '0', '176', '-99', '0');
INSERT INTO `picture` VALUES ('166', '75', 'images/album/2016/04/12/1460457454866.png', '0', '176', '-99', '0');
INSERT INTO `picture` VALUES ('167', '75', 'images/album/2016/04/12/1460457455419.png', '0', '176', '-99', '0');
INSERT INTO `picture` VALUES ('168', '76', 'images/album/2016/04/12/1460457917915.jpg', '0', '176', '-9', '0');
INSERT INTO `picture` VALUES ('169', '76', 'images/album/2016/04/12/1460457921398.jpg', '0', '176', '-31', '0');
INSERT INTO `picture` VALUES ('170', '76', 'images/album/2016/04/12/1460457924885.jpg', '0', '176', '-31', '0');
INSERT INTO `picture` VALUES ('171', '76', 'images/album/2016/04/12/1460457926364.jpg', '0', '176', '-31', '0');
INSERT INTO `picture` VALUES ('184', '82', 'images/album/2016/04/12/1460460684423.jpg', '0', '176', '-31', '0');
INSERT INTO `picture` VALUES ('185', '82', 'images/album/2016/04/12/1460460685233.jpg', '114', '0', '0', '-13');
INSERT INTO `picture` VALUES ('186', '82', 'images/album/2016/04/12/1460460720657.jpg', '0', '176', '-60', '0');
INSERT INTO `picture` VALUES ('187', '83', 'images/album/2016/04/12/1460460743286.jpg', '0', '176', '-31', '0');
INSERT INTO `picture` VALUES ('188', '84', 'images/album/2016/04/12/1460460878873.jpg', '0', '176', '-31', '0');
INSERT INTO `picture` VALUES ('189', '85', 'images/album/2016/04/12/1460461904418.jpg', '114', '0', '0', '-13');
INSERT INTO `picture` VALUES ('190', '85', 'images/album/2016/04/12/1460461928430.jpg', '0', '176', '-60', '0');
INSERT INTO `picture` VALUES ('191', '85', 'images/album/2016/04/12/1460461934933.png', '0', '176', '-99', '0');
INSERT INTO `picture` VALUES ('192', '85', 'images/album/2016/04/12/1460461961394.jpg', '0', '176', '-60', '0');
INSERT INTO `picture` VALUES ('193', '85', 'images/album/2016/04/12/1460461963473.jpg', '0', '176', '-31', '0');
INSERT INTO `picture` VALUES ('194', '85', 'images/album/2016/04/12/1460461964746.jpg', '0', '176', '-31', '0');
INSERT INTO `picture` VALUES ('195', '85', 'images/album/2016/04/12/1460461966210.png', '0', '176', '-99', '0');
INSERT INTO `picture` VALUES ('196', '85', 'images/album/2016/04/12/1460461966981.png', '0', '176', '-99', '0');
INSERT INTO `picture` VALUES ('197', '85', 'images/album/2016/04/12/1460461968459.png', '0', '176', '-99', '0');
INSERT INTO `picture` VALUES ('207', '87', 'images/album/2016/04/12/1460466789981.jpg', '0', '176', '-60', '0');
INSERT INTO `picture` VALUES ('208', '87', 'images/album/2016/04/12/1460466794542.jpg', '0', '176', '-60', '0');
INSERT INTO `picture` VALUES ('209', '87', 'images/album/2016/04/12/1460466796457.png', '0', '176', '-99', '0');
INSERT INTO `picture` VALUES ('210', '87', 'images/album/2016/04/12/1460466797309.png', '0', '176', '-99', '0');
INSERT INTO `picture` VALUES ('211', '87', 'images/album/2016/04/12/1460466799171.jpg', '0', '176', '-60', '0');
INSERT INTO `picture` VALUES ('212', '87', 'images/album/2016/04/12/1460466802216.jpg', '0', '176', '-9', '0');
INSERT INTO `picture` VALUES ('213', '87', 'images/album/2016/04/12/1460466813749.jpg', '0', '176', '-9', '0');
INSERT INTO `picture` VALUES ('214', '87', 'images/album/2016/04/12/1460466816364.png', '0', '176', '-99', '0');
INSERT INTO `picture` VALUES ('215', '87', 'images/album/2016/04/12/1460466817176.png', '0', '176', '-99', '0');
INSERT INTO `picture` VALUES ('216', '88', 'images/album/2016/04/13/1460520503393.png', '0', '176', '-99', '0');
INSERT INTO `picture` VALUES ('217', '88', 'images/album/2016/04/13/1460520504278.png', '0', '176', '-99', '0');
INSERT INTO `picture` VALUES ('218', '88', 'images/album/2016/04/13/1460520504689.png', '0', '176', '-99', '0');
INSERT INTO `picture` VALUES ('219', '88', 'images/album/2016/04/13/1460520504990.png', '0', '176', '-99', '0');
INSERT INTO `picture` VALUES ('220', '88', 'images/album/2016/04/13/1460520505369.png', '0', '176', '-99', '0');
INSERT INTO `picture` VALUES ('221', '88', 'images/album/2016/04/13/1460520505634.png', '0', '176', '-99', '0');
INSERT INTO `picture` VALUES ('222', '88', 'images/album/2016/04/13/1460520505873.png', '0', '176', '-99', '0');
INSERT INTO `picture` VALUES ('223', '88', 'images/album/2016/04/13/1460520506110.png', '0', '176', '-99', '0');
INSERT INTO `picture` VALUES ('224', '88', 'images/album/2016/04/13/1460520506586.png', '0', '176', '-99', '0');
INSERT INTO `picture` VALUES ('225', '90', 'images/album/2016/04/14/1460619933132.jpg', '0', '176', '-31', '0');
INSERT INTO `picture` VALUES ('226', '91', 'images/album/2016/04/14/1460619971516.jpg', '0', '176', '-9', '0');
INSERT INTO `picture` VALUES ('227', '91', 'images/album/2016/04/14/1460619972120.jpg', '0', '176', '-31', '0');
INSERT INTO `picture` VALUES ('228', '91', 'images/album/2016/04/14/1460619972517.jpg', '0', '176', '-31', '0');
INSERT INTO `picture` VALUES ('229', '91', 'images/album/2016/04/14/1460619972886.jpg', '0', '176', '-31', '0');
INSERT INTO `picture` VALUES ('230', '91', 'images/album/2016/04/14/1460619973177.jpg', '0', '176', '-31', '0');
INSERT INTO `picture` VALUES ('231', '91', 'images/album/2016/04/14/1460619973526.jpg', '0', '176', '-31', '0');
INSERT INTO `picture` VALUES ('232', '91', 'images/album/2016/04/14/1460619973997.jpg', '114', '0', '0', '-13');
INSERT INTO `picture` VALUES ('233', '91', 'images/album/2016/04/14/1460619974642.png', '0', '176', '-99', '0');
INSERT INTO `picture` VALUES ('234', '91', 'images/album/2016/04/14/1460619976322.jpg', '0', '176', '-60', '0');
INSERT INTO `picture` VALUES ('235', '92', 'images/album/2016/04/16/1460765853636.jpg', '0', '176', '-9', '0');
INSERT INTO `picture` VALUES ('236', '93', 'images/album/2016/04/16/1460766473255.jpg', '0', '176', '-9', '0');
INSERT INTO `picture` VALUES ('237', '93', 'images/album/2016/04/16/1460766473518.png', '0', '176', '-99', '0');
INSERT INTO `picture` VALUES ('238', '93', 'images/album/2016/04/16/1460766473817.png', '0', '176', '-99', '0');
INSERT INTO `picture` VALUES ('239', '93', 'images/album/2016/04/16/1460766474377.png', '0', '176', '-99', '0');
INSERT INTO `picture` VALUES ('240', '93', 'images/album/2016/04/16/1460766474593.png', '0', '176', '-99', '0');
INSERT INTO `picture` VALUES ('241', '93', 'images/album/2016/04/16/1460766474811.png', '0', '176', '-99', '0');
INSERT INTO `picture` VALUES ('242', '93', 'images/album/2016/04/16/1460766475036.png', '0', '176', '-99', '0');
INSERT INTO `picture` VALUES ('243', '93', 'images/album/2016/04/16/1460766475262.jpg', '0', '176', '-31', '0');
INSERT INTO `picture` VALUES ('244', '93', 'images/album/2016/04/16/1460766476856.jpg', '0', '176', '-60', '0');
INSERT INTO `picture` VALUES ('253', '111', 'images/album/2016/04/23/1461405093616.jpg', '0', '176', '-21', '0');
INSERT INTO `picture` VALUES ('254', '111', 'images/album/2016/04/23/1461405094177.png', '0', '176', '-99', '0');
INSERT INTO `picture` VALUES ('255', '114', 'images/album/2016/04/30/1462031336754.png', '114', '0', '0', '-19');
INSERT INTO `picture` VALUES ('256', '114', 'images/album/2016/04/30/1462031341250.png', '114', '0', '0', '-19');
INSERT INTO `picture` VALUES ('257', '114', 'images/album/2016/04/30/1462031342955.png', '114', '0', '0', '-19');
INSERT INTO `picture` VALUES ('258', '114', 'images/album/2016/04/30/1462031343467.png', '114', '0', '0', '-19');
INSERT INTO `picture` VALUES ('259', '114', 'images/album/2016/04/30/1462031344068.png', '114', '0', '0', '-19');
INSERT INTO `picture` VALUES ('269', '116', 'images/album/2016/05/06/1462500455493.jpg', '0', '176', '-60', '0');
INSERT INTO `picture` VALUES ('270', '116', 'images/album/2016/05/06/1462500458279.jpg', '0', '176', '-60', '0');
INSERT INTO `picture` VALUES ('271', '116', 'images/album/2016/05/06/1462500459972.jpg', '0', '176', '-31', '0');
INSERT INTO `picture` VALUES ('272', '116', 'images/album/2016/05/06/1462500460244.jpg', '0', '176', '-31', '0');
INSERT INTO `picture` VALUES ('273', '116', 'images/album/2016/05/06/1462500460809.jpg', '0', '176', '-31', '0');
INSERT INTO `picture` VALUES ('274', '116', 'images/album/2016/05/06/1462500461103.jpg', '0', '176', '-31', '0');
INSERT INTO `picture` VALUES ('275', '116', 'images/album/2016/05/06/1462500461342.jpg', '0', '176', '-31', '0');
INSERT INTO `picture` VALUES ('276', '116', 'images/album/2016/05/06/1462500461538.jpg', '0', '176', '-75', '0');
INSERT INTO `picture` VALUES ('277', '116', 'images/album/2016/05/06/1462500461754.jpg', '114', '0', '0', '-13');
INSERT INTO `picture` VALUES ('278', '117', 'images/album/2016/05/13/1463115821908.jpg', '0', '176', '-9', '0');
INSERT INTO `picture` VALUES ('279', '117', 'images/album/2016/05/13/1463115831164.jpg', '0', '176', '-9', '0');
INSERT INTO `picture` VALUES ('280', '117', 'images/album/2016/05/13/1463115839408.jpg', '0', '176', '-9', '0');
INSERT INTO `picture` VALUES ('281', '117', 'images/album/2016/05/13/1463115851306.JPG', '114', '0', '0', '-13');
INSERT INTO `picture` VALUES ('282', '118', 'images/album/2016/05/14/1463193874121.jpg', '0', '176', '-31', '0');
INSERT INTO `picture` VALUES ('283', '119', 'images/album/2016/05/14/1463203180928.jpg', '0', '176', '-9', '0');
INSERT INTO `picture` VALUES ('284', '119', 'images/album/2016/05/14/1463203187388.jpg', '0', '176', '-9', '0');
INSERT INTO `picture` VALUES ('285', '120', 'images/album/2016/05/14/1463203520557.jpg', '0', '176', '-75', '0');
INSERT INTO `picture` VALUES ('286', '120', 'images/album/2016/05/14/1463203520777.jpg', '0', '176', '-21', '0');
INSERT INTO `picture` VALUES ('287', '120', 'images/album/2016/05/14/1463203520924.jpg', '114', '0', '0', '-13');
INSERT INTO `picture` VALUES ('288', '120', 'images/album/2016/05/14/1463203522046.jpg', '0', '176', '-60', '0');
INSERT INTO `picture` VALUES ('289', '121', 'images/album/2016/07/25/1469422633953.jpg', '0', '176', '-60', '0');
INSERT INTO `picture` VALUES ('290', '121', 'images/album/2016/07/25/1469422635746.jpg', '0', '176', '-99', '0');
INSERT INTO `picture` VALUES ('291', '123', 'images/album/2016/12/15/1481810998359.jpg', '0', '176', '-99', '0');
INSERT INTO `picture` VALUES ('292', '123', 'images/album/2016/12/15/1481810998920.jpg', '0', '176', '-99', '0');
INSERT INTO `picture` VALUES ('293', '123', 'images/album/2016/12/15/1481810999245.jpg', '0', '176', '-105', '0');
INSERT INTO `picture` VALUES ('294', '123', 'images/album/2016/12/15/1481810999459.jpg', '114', '0', '0', '-13');

-- ----------------------------
-- Table structure for recall
-- ----------------------------
DROP TABLE IF EXISTS `recall`;
CREATE TABLE `recall` (
  `RecallNo` bigint(20) NOT NULL AUTO_INCREMENT,
  `UserNo` bigint(20) DEFAULT NULL,
  `Content` varchar(10000) NOT NULL,
  `PublishTime` varchar(20) NOT NULL,
  PRIMARY KEY (`RecallNo`),
  KEY `Reference_Recall_User` (`UserNo`),
  CONSTRAINT `Reference_Recall_User` FOREIGN KEY (`UserNo`) REFERENCES `user` (`UserNo`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=124 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of recall
-- ----------------------------
INSERT INTO `recall` VALUES ('2', '10002', '活出生命质量似乎是人们亘古不变的话题。生命究竟是生存还是生活？是苟延残喘、按部就班、碌碌无为，还是点亮生命、为爱而生、活出精彩？', '2015-12-20 15:31:32');
INSERT INTO `recall` VALUES ('8', '10009', '写着写着都不清楚自己想要表达什么思想感情了，可能是想着你占用了我的心却不能坦白的拥有你而难过吧，这种想爱不能爱，想表达却无处倾述的地方而隐隐约约让心里灼痛。我也是读书之人，虽说不是飞黄腾达，但也懂得怎么尊重人，可是这件事我自己都无法原谅我自己，也许单是这一点我足够犯了错，所以才这样用心灵的折磨的方式来惩罚我，让我永远无法摆脱你在我心里的阴影。', '2015-12-22 18:08:55');
INSERT INTO `recall` VALUES ('9', '10007', '因此我早也芳心暗许，小心谨慎的想着、看着守护着我心中的悬着的那份执着的恋头，我一直都有目标、都有理想、有远见、目光长远，但这次的自己，彻底发生了翻天覆地的改变。我只要看着他，看着目前，只想要现在的过程，不要结果，要我自己真实的感受，我怎么了，我到底怎么了，我反反复复问着自己，小雨晨你的爱情早也睡醒了，你醒醒吧，我多次呼唤着自己，我努力让自己清醒，但是控制力太差，每一天你在我脑海里不停得出现，有时间我认为一个逃避你，不想看见你；有时间又认为我应该看着你；有时又觉得要关心你，却有想着不应该担心你。', '2015-12-22 18:09:47');
INSERT INTO `recall` VALUES ('10', '10008', 'There are moments in life when you miss someone so much that you just want to pick them from your dreams and hug them for real! Dream what you want to dream;go where you want to go;be what you want to be,because you have only one life and one chance to do all the things you want to do.', '2015-12-22 18:10:49');
INSERT INTO `recall` VALUES ('11', '10006', '连环计杀董卓。连环计虽然是王允的绝妙策划，但真正的实施者是貂蝉。在《三国演义》之中，董卓祸乱朝纲，残忍暴戾，滥杀无辜，百姓深受其害。而群臣皆岌岌自危，即便枭雄曹操亦是行刺失败而亡命天涯。由于貂蝉的出现，才有了王司徒巧施连环计的佳话，才有了吕奉先大闹凤仪亭的风波，才有了凶横无忌权倾一时的董卓宫门前的被戮，才有了武功盖世吕布的白门楼上的殒命', '2015-12-22 18:11:00');
INSERT INTO `recall` VALUES ('14', '10008', '瑜以凡才，荷蒙殊遇，委任腹心，统御兵马，敢不竭股肱之力，以图报效。奈死生不测，修短有命；愚志未展，微躯已殒，遗恨何极！方今曹操在北，疆场未静；刘备寄寓，有似养虎；天下之事，尚未可知。此正朝士旰食之秋，至尊垂虑之日也。鲁肃忠烈，临事不苟，可以代瑜之任。人之将死，其言也善。倘蒙垂鉴，瑜死不朽矣。', '2015-12-22 22:48:14');
INSERT INTO `recall` VALUES ('16', '10003', '孔明曰：倘曹操引兵来到，当如之何？云长曰：以力拒之。孔明又曰：倘曹操、孙权，齐起兵来，如之奈何？云长曰：分兵拒之。孔明曰：若如此，荆州危矣。吾有八个字，将军牢记，可保守荆州。云长问：那八个字？孔明曰：北拒曹操，东和孙权。云长曰：军师之言，当铭肺腑。', '2015-12-22 22:53:04');
INSERT INTO `recall` VALUES ('17', '10005', '曹操招兵买马，会合袁绍、公孙瓒、孙坚等十七路兵马，攻打董卓。 刘备、关羽和张飞追随公孙瓒一同前往。董卓大将华雄打败了十八路兵马的先锋孙坚，又在阵前杀了两员大将，非常得意。 十八路诸侯都很惊慌，束手无策，袁绍说：“可惜我的大将颜良、文丑不在，不然，就不怕华雄了。” 话音刚落， 关羽高声叫道：“小将愿意去砍下华雄的脑袋！” 袁绍认为关羽不过是个马弓手，就生气地说，“我们十八路诸侯大将几百员，却要派一个马弓手出战，岂不让华雄笑话。” 关羽大声说：“我如果杀不了华雄， 就请砍下我的脑袋。” 曹操听了，十分欣赏，就倒了一杯热酒，递给关羽说：“将军喝了这杯酒，再去杀敌。” 关羽接过酒杯，放在桌上说：“等我回来再喝吧！” 说完，提着大刀上马去了。 关羽武艺高强，没一会儿，就砍下了华雄的脑袋。他回到军营，曹操忙拿起桌上的酒杯递给他，杯中的酒还没凉呢。', '2015-12-22 22:55:07');
INSERT INTO `recall` VALUES ('18', '10002', '张飞（？－221年），字益德[1]  ，幽州涿郡（今河北省保定市涿州市）人氏，三国时期蜀汉名将。刘备长坂坡败退，张飞仅率二十骑断后，据水断桥，曹军没人敢逼近；与诸葛亮、赵云扫荡西川时，于江州义释严颜；汉中之战时又于宕渠击败张郃，对蜀汉贡献极大，官至车骑将军、领司隶校尉，封西乡侯，后被范强、张达刺杀。后主时代追谥为“桓侯”。', '2015-12-22 22:57:15');
INSERT INTO `recall` VALUES ('33', '10001', '<div><div id=\"frameAddHead\"></div><div><span class=\"Apple-tab-span\" style=\"white-space:pre\">				</span><span id=\"frameAddBtn\" class=\"frameAddBtnPost\" onclick=\"HomeOperateUtil.homeAddPanelShow()\"></span></div><div><span class=\"Apple-tab-span\" style=\"white-space:pre\">			</span></div></div>', '2016-01-20 19:13:47');
INSERT INTO `recall` VALUES ('36', '10001', '汉中之战时又于宕渠击败张郃，对蜀汉贡献极大，官至车骑将军、领司隶校尉，封西乡侯，后被范强、张达刺杀', '2016-01-24 13:22:20');
INSERT INTO `recall` VALUES ('39', '10002', '话说有人说这小子长得挺帅的。。。这张自拍我给一百个赞！！！', '2016-03-23 16:25:24');
INSERT INTO `recall` VALUES ('40', '10004', '最近好闲呢？我的兄弟们都不搭理我。。。发张自拍，乐呵乐呵', '2016-03-26 09:37:58');
INSERT INTO `recall` VALUES ('41', '10006', '我长得是不是很美？闭月羞花，沉鱼落雁，，，哼哼，我感觉自己美得不要不要的。你们都来点赞，，，本女王大人才会开心。吼吼吼', '2016-03-26 17:54:54');
INSERT INTO `recall` VALUES ('42', '10009', '我相信，你不会忘记我。。。。。', '2016-04-01 13:40:52');
INSERT INTO `recall` VALUES ('43', '10009', '', '2016-04-01 13:41:56');
INSERT INTO `recall` VALUES ('44', '10007', '不说话，只发图！', '2016-04-01 13:44:53');
INSERT INTO `recall` VALUES ('45', '10007', '刚不是我发的。。。别见怪', '2016-04-01 13:45:39');
INSERT INTO `recall` VALUES ('46', '10006', '有一首歌叫胡歌', '2016-04-01 13:49:11');
INSERT INTO `recall` VALUES ('47', '10006', '最爱胡歌。。。大暖男，萌boy', '2016-04-01 13:49:54');
INSERT INTO `recall` VALUES ('48', '10001', '范德萨范德萨发', '2016-04-05 18:06:34');
INSERT INTO `recall` VALUES ('49', '10001', '曾经西瓜 的小伙伴们，，，你们还好吗', '2016-04-05 19:42:49');
INSERT INTO `recall` VALUES ('50', '10001', '我们一起吃蛋糕', '2016-04-05 19:59:24');
INSERT INTO `recall` VALUES ('52', '10001', 'np小说蒸蒸日上无语去曲终人未散万岁万岁万万岁无依然在一起庸人自扰之水至清则无鱼人无完人正月十五在一起万岁万岁万万岁庸人自扰之早睡早起万岁万岁万万岁庸人自扰之曲终人未散庸人自扰之一万岁万岁万万岁庸人自扰之水至清则无鱼一样嘻嘻一些事一些情早睡早起绣十字绣早睡早起一无所有壮士一去兮嘻嘻在一起庸人自扰之婆婆婆婆吼吼吼吼早睡早起在上自习资金明年\n嘻嘻嘻嘻妮子9名敏敏明明明明您', '2016-04-12 16:04:50');
INSERT INTO `recall` VALUES ('72', '10006', '一万岁万岁万万岁绣十字绣', '2016-04-12 18:34:58');
INSERT INTO `recall` VALUES ('73', '10006', '急溜溜故意无所谓鱼死网破送', '2016-04-12 18:35:50');
INSERT INTO `recall` VALUES ('74', '10006', '麻烦你机会8曲扑通扑通去去', '2016-04-12 18:36:35');
INSERT INTO `recall` VALUES ('75', '10006', '难道真的好了么', '2016-04-12 18:37:35');
INSERT INTO `recall` VALUES ('76', '10009', '我是小人物普陀区', '2016-04-12 18:45:26');
INSERT INTO `recall` VALUES ('82', '10009', '144', '2016-04-12 19:32:02');
INSERT INTO `recall` VALUES ('83', '10009', '', '2016-04-12 19:32:23');
INSERT INTO `recall` VALUES ('84', '10001', '看看', '2016-04-12 19:34:44');
INSERT INTO `recall` VALUES ('85', '10009', '', '2016-04-12 19:52:49');
INSERT INTO `recall` VALUES ('87', '10002', '最后的测试', '2016-04-12 21:13:37');
INSERT INTO `recall` VALUES ('88', '10001', '图咯6组V5流量监控默默', '2016-04-13 12:08:26');
INSERT INTO `recall` VALUES ('89', '10001', '就会退', '2016-04-14 15:43:09');
INSERT INTO `recall` VALUES ('90', '10001', '该哭就哭', '2016-04-14 15:45:34');
INSERT INTO `recall` VALUES ('91', '10001', '家里冷路', '2016-04-14 15:46:18');
INSERT INTO `recall` VALUES ('92', '10001', '', '2016-04-16 08:17:37');
INSERT INTO `recall` VALUES ('93', '10001', '哈哈哈', '2016-04-16 08:27:58');
INSERT INTO `recall` VALUES ('110', '10001', '照片勿施于人万岁万岁万万岁我以为是一些事一些情万岁万岁万万岁万岁万岁万万岁依然在一起早睡早起绣十字绣嘻嘻熊猫', '2016-04-18 21:12:19');
INSERT INTO `recall` VALUES ('111', '10002', 'vgghh', '2016-04-23 17:51:35');
INSERT INTO `recall` VALUES ('112', '10011', '我是天下第一大帅比，尼古拉斯.米开朗基罗.帅.慕斯特.周', '2016-04-23 17:55:17');
INSERT INTO `recall` VALUES ('114', '10002', 'np小说万岁万岁万万岁我', '2016-04-30 23:49:04');
INSERT INTO `recall` VALUES ('116', '10005', '我在写毕业论文…', '2016-05-06 10:07:41');
INSERT INTO `recall` VALUES ('117', '10003', '欲把西湖比西子，淡妆浓抹总相宜', '2016-05-13 13:04:13');
INSERT INTO `recall` VALUES ('118', '10002', '我正在准备答辩', '2016-05-14 10:44:34');
INSERT INTO `recall` VALUES ('119', '10002', '恢复到敬爱是否和第三', '2016-05-14 13:19:51');
INSERT INTO `recall` VALUES ('120', '10002', '旅途去up玉田县', '2016-05-14 13:25:24');
INSERT INTO `recall` VALUES ('121', '10003', '他去就S级记录', '2016-07-25 12:57:17');
INSERT INTO `recall` VALUES ('122', '10005', 'heieihei', '2016-12-15 22:54:17');
INSERT INTO `recall` VALUES ('123', '10012', 'yyh', '2016-12-15 23:09:59');

-- ----------------------------
-- Table structure for respon
-- ----------------------------
DROP TABLE IF EXISTS `respon`;
CREATE TABLE `respon` (
  `ResponNo` bigint(20) NOT NULL AUTO_INCREMENT,
  `CommentNo` bigint(20) NOT NULL,
  `Content` varchar(1000) NOT NULL,
  `AddTime` varchar(20) NOT NULL,
  `FromNo` bigint(20) NOT NULL,
  `ToNo` bigint(20) NOT NULL,
  PRIMARY KEY (`ResponNo`),
  KEY `Reference_Respon_Comment` (`CommentNo`),
  CONSTRAINT `Reference_Respon_Comment` FOREIGN KEY (`CommentNo`) REFERENCES `comment` (`CommentNo`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of respon
-- ----------------------------
INSERT INTO `respon` VALUES ('11', '5', '欢迎主公。。。。。。。。', '2015-12-27 14:33:28', '10002', '10004');
INSERT INTO `respon` VALUES ('12', '5', '主公最近身体可好？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？', '2015-12-27 14:35:23', '10002', '10004');
INSERT INTO `respon` VALUES ('14', '19', '伐开心啊', '2016-01-24 13:28:36', '10002', '10004');
INSERT INTO `respon` VALUES ('15', '18', '你就是我的最爱。。。我永远不会忘记。', '2016-03-26 09:27:25', '10001', '10004');
INSERT INTO `respon` VALUES ('19', '46', '弟弟带兵辛苦了。回来就给你找个妹子。。。你看可否？', '2016-03-26 09:45:37', '10004', '10005');
INSERT INTO `respon` VALUES ('22', '63', '测试OK9只现在就可以咯', '2016-04-02 21:16:39', '10001', '10001');
INSERT INTO `respon` VALUES ('24', '65', '滴滴滴滴', '2016-04-02 21:17:24', '10001', '10001');
INSERT INTO `respon` VALUES ('25', '48', '美美哒……', '2016-04-03 17:49:14', '10001', '10006');
INSERT INTO `respon` VALUES ('26', '68', '5句吐了', '2016-04-05 18:45:58', '10002', '10001');
INSERT INTO `respon` VALUES ('27', '68', '曹公？是你么～～～好久没见……感觉你又帅气了  羡慕嫉妒恨啊……我主公对你日思夜想的～什么时候有空？我们聚一聚呗', '2016-04-05 19:12:30', '10003', '10001');
INSERT INTO `respon` VALUES ('28', '68', '医德～最近可还好～', '2016-04-05 19:13:06', '10003', '10002');
INSERT INTO `respon` VALUES ('29', '70', '我也觉得……西瓜的小伙伴好棒！', '2016-04-08 00:30:48', '10006', '10003');
INSERT INTO `respon` VALUES ('30', '71', '第三张是小郭啊…你仔细瞅瞅', '2016-04-08 00:36:24', '10008', '10006');
INSERT INTO `respon` VALUES ('31', '61', '你瞎念叨什么呢？', '2016-04-08 00:37:08', '10008', '10001');
INSERT INTO `respon` VALUES ('32', '72', '公瑾近来可好了？(  ૢ⁼̴̤̆ ㉨ ⁼̴̤̆ ૢ)♡ 约吗？', '2016-04-08 00:39:27', '10004', '10008');
INSERT INTO `respon` VALUES ('33', '71', '第三张是郭子啊……', '2016-04-08 00:39:46', '10004', '10006');
INSERT INTO `respon` VALUES ('34', '74', '主公，身体可好？', '2016-04-08 00:41:14', '10007', '10004');
INSERT INTO `respon` VALUES ('35', '71', '你家相公可好？请转告吕布，他日我定取他项上人头', '2016-04-08 00:42:50', '10007', '10006');
INSERT INTO `respon` VALUES ('36', '71', '嗯嗯  知道了', '2016-04-08 00:43:19', '10006', '10008');
INSERT INTO `respon` VALUES ('37', '71', '嗯嗯 刚认出来', '2016-04-08 00:43:37', '10006', '10004');
INSERT INTO `respon` VALUES ('38', '71', '不要伤害我夫君…', '2016-04-08 00:43:58', '10006', '10007');
INSERT INTO `respon` VALUES ('39', '74', '你想啥呢…', '2016-04-08 00:44:16', '10006', '10004');
INSERT INTO `respon` VALUES ('40', '75', '子龙哥哥好', '2016-04-08 00:44:30', '10006', '10007');
INSERT INTO `respon` VALUES ('41', '5', '✧*｡٩(^㉨^*)و✧*｡', '2016-04-08 00:50:44', '10001', '10004');
INSERT INTO `respon` VALUES ('42', '74', '主公好（⊙㉨⊙）', '2016-04-08 00:52:27', '10003', '10004');
INSERT INTO `respon` VALUES ('43', '53', '敏敏', '2016-04-09 20:11:32', '10006', '10001');
INSERT INTO `respon` VALUES ('44', '14', '好的好的回电话发货好烦呐放哪你打', '2016-04-18 18:02:04', '10003', '10001');
INSERT INTO `respon` VALUES ('45', '86', '快去快去取信', '2016-04-30 23:49:33', '10002', '10001');
INSERT INTO `respon` VALUES ('46', '86', 'yhuhuihu', '2016-04-30 23:49:44', '10001', '10002');
INSERT INTO `respon` VALUES ('47', '71', '生活好自在哦', '2016-05-06 11:39:07', '10005', '10006');
INSERT INTO `respon` VALUES ('48', '87', 'asd', '2016-12-15 23:05:39', '10005', '10002');
INSERT INTO `respon` VALUES ('49', '81', '吧', '2016-12-15 23:14:52', '10005', '10009');
INSERT INTO `respon` VALUES ('50', '82', '饿的', '2016-12-15 23:15:03', '10005', '10009');
INSERT INTO `respon` VALUES ('51', '81', '可口可乐', '2016-12-15 23:15:10', '10005', '10009');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `UserNo` bigint(20) NOT NULL AUTO_INCREMENT,
  `NickName` varchar(100) DEFAULT NULL,
  `UserPsd` varchar(100) NOT NULL,
  `Mobile` varchar(11) NOT NULL,
  `Autograph` varchar(100) DEFAULT NULL,
  `Gender` smallint(6) DEFAULT '-1',
  `Status` smallint(6) DEFAULT '0',
  `RegistTime` varchar(20) NOT NULL,
  `Birthday` varchar(10) DEFAULT NULL,
  `HomeTown` varchar(100) DEFAULT NULL,
  `Location` varchar(100) DEFAULT NULL,
  `Email` varchar(50) DEFAULT NULL,
  `PicPath` varchar(100) DEFAULT NULL,
  `IsValid` tinyint(1) DEFAULT '1',
  `SmallNick` varchar(200) DEFAULT NULL,
  `IsOnline` bit(1) DEFAULT b'0',
  `WallPaper` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`UserNo`)
) ENGINE=InnoDB AUTO_INCREMENT=10013 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('10001', '小郭子啦', '11111111', '18817800144', '分可口可乐了可口可乐了空间可口可乐了', '1', '0', '2015-12-11 11:25:56', '1994-04-16', '山西-晋城-陵川县', '上海-浦东新区', '', 'images/head/2016/04/15/1460706414855.jpg', '1', '郭子', '\0', 'images/wallpaper/2016/04/23/1461401802071.jpg');
INSERT INTO `user` VALUES ('10002', '张翼德', '11111111', '18817800145', '哥哥…你在哪？', '1', '0', '2015-12-12 11:25:56', '1995-04-26', '上海-浦东新区', '重庆', '', 'images/head/2016/05/14/1463203378139.jpg', '1', '', '', 'images/wallpaper/2016/04/05/2.png');
INSERT INTO `user` VALUES ('10003', '诸葛孔明', '11111111', '18817800146', '鞠躬尽瘁，死而后已', '0', '0', '2015-12-13 11:25:56', '2009-01-28', '河南-平顶山-新华区', '湖北-神农架-神农架', '', 'images/head/2015/12/13/10003.png', '1', '亮亮', '', 'images/wallpaper/2016/04/05/3.png');
INSERT INTO `user` VALUES ('10004', '刘玄德', '11111111', '18817800147', '匡扶天下汉室，乃吾之使命', '1', '0', '2015-12-14 11:25:56', null, null, null, null, 'images/head/2015/12/14/10004.png', '1', null, '', 'images/wallpaper/2016/04/05/4.png');
INSERT INTO `user` VALUES ('10005', '关云长', '11111111', '18817800148', '桃园一拜,恩义常在!', '0', '0', '2015-12-15 15:05:09', '', '山西-晋城-陵川县', '', '', 'images/head/2016/12/15/1481811115005.jpg', '1', '', '', 'images/wallpaper/2016/04/05/1.png');
INSERT INTO `user` VALUES ('10006', '貂蝉', '11111111', '18817800149', '吕公，此生我愿永远随你...', '0', '0', '2015-12-16 15:05:09', null, null, null, null, 'images/head/2015/12/16/10006.png', '1', null, '\0', 'images/wallpaper/2016/04/05/2.png');
INSERT INTO `user` VALUES ('10007', '赵子龙', '11111111', '18817800150', '曹贼休走，待吾去你狗命', '1', '0', '2015-12-17 15:05:09', null, null, null, null, 'images/head/2015/12/17/10007.png', '1', null, '\0', 'images/wallpaper/2016/04/05/3.png');
INSERT INTO `user` VALUES ('10008', '周公瑾', '11111111', '18817800151', '既生瑜，何生亮', '1', '0', '2015-12-18 15:05:09', null, null, null, null, 'images/head/2015/12/18/10008.png', '1', null, '\0', 'images/wallpaper/2016/04/05/4.png');
INSERT INTO `user` VALUES ('10009', '吕奉先', '11111111', '18817800152', '我不是三姓家奴。生活无奈啊！', '1', '0', '2015-12-19 15:05:09', null, null, null, null, 'images/head/2015/12/19/10009.png', '1', null, '\0', 'images/wallpaper/2016/04/12/1460451388371.jpg');
INSERT INTO `user` VALUES ('10010', '啦啦', '11111111', '18817800153', '你民工您你民工您民工', '0', '0', '2016-04-18 19:18:56', '2008-04-19', '湖北-神农架-神农架', '天津-宁河区', '', 'images/head/2016/04/18/1460978371636.jpg', '1', '', '\0', null);
INSERT INTO `user` VALUES ('10011', '周瑜', '11111111', '18818223559', '遥想公瑾当年,羽扇纶巾', '1', '0', '2016-04-23 17:53:38', '', '', '', '', 'images/head/2016/04/23/1461405604904.jpg', '1', '', '\0', null);
INSERT INTO `user` VALUES ('10012', '1481810908090', '11111111', '13800000000', null, '1', '0', '2016-12-15 23:08:28', null, null, null, null, 'images/head/2016/12/15/1481810978169.jpg', '1', null, '\0', null);

-- ----------------------------
-- Table structure for verifycode
-- ----------------------------
DROP TABLE IF EXISTS `verifycode`;
CREATE TABLE `verifycode` (
  `VerifyCodeNo` bigint(20) NOT NULL AUTO_INCREMENT,
  `Code` varchar(6) NOT NULL,
  `AddTime` varchar(20) NOT NULL,
  `Mobile` varchar(11) NOT NULL,
  `Type` smallint(6) NOT NULL,
  `Valid` tinyint(1) NOT NULL,
  PRIMARY KEY (`VerifyCodeNo`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of verifycode
-- ----------------------------
INSERT INTO `verifycode` VALUES ('1', '4628', '2015-12-22 21:31:54', '18817800148', '1', '1');
INSERT INTO `verifycode` VALUES ('2', '4043', '2015-12-24 14:42:38', '18817800148', '1', '1');
INSERT INTO `verifycode` VALUES ('3', '3338', '2015-12-24 14:43:07', '18817800144', '2', '0');
INSERT INTO `verifycode` VALUES ('4', '9823', '2016-01-03 12:05:19', '18817800148', '1', '1');
INSERT INTO `verifycode` VALUES ('5', '6153', '2016-01-13 10:42:54', '18817800148', '1', '1');
INSERT INTO `verifycode` VALUES ('6', '9468', '2016-04-09 00:37:24', '18817800148', '1', '1');
INSERT INTO `verifycode` VALUES ('7', '8601', '2016-04-09 00:37:34', '18817800148', '1', '1');
INSERT INTO `verifycode` VALUES ('8', '3183', '2016-04-09 00:40:13', '18817800148', '1', '1');
INSERT INTO `verifycode` VALUES ('9', '9519', '2016-04-09 00:48:48', '18817800153', '2', '1');
INSERT INTO `verifycode` VALUES ('10', '7350', '2016-04-09 09:17:30', '18817800140', '1', '1');
INSERT INTO `verifycode` VALUES ('11', '0064', '2016-04-09 10:20:07', '18817800153', '1', '0');
INSERT INTO `verifycode` VALUES ('12', '4369', '2016-04-09 10:26:54', '18817800144', '2', '1');
INSERT INTO `verifycode` VALUES ('13', '0815', '2016-04-09 10:34:23', '18817800148', '2', '1');
INSERT INTO `verifycode` VALUES ('14', '3498', '2016-04-09 10:37:09', '18817800144', '2', '1');
INSERT INTO `verifycode` VALUES ('15', '3507', '2016-04-09 10:46:31', '18817800148', '2', '0');
INSERT INTO `verifycode` VALUES ('16', '3213', '2016-04-09 12:45:26', '18817800144', '2', '1');
INSERT INTO `verifycode` VALUES ('17', '8094', '2016-04-09 12:46:31', '18817800144', '2', '0');
INSERT INTO `verifycode` VALUES ('18', '8329', '2016-04-09 12:48:48', '18817800148', '2', '1');
INSERT INTO `verifycode` VALUES ('19', '8928', '2016-04-09 12:49:25', '18817800133', '1', '1');
INSERT INTO `verifycode` VALUES ('20', '8717', '2016-04-09 18:33:10', '18817800144', '2', '0');
INSERT INTO `verifycode` VALUES ('21', '1382', '2016-04-09 20:42:40', '18817800142', '2', '1');
INSERT INTO `verifycode` VALUES ('22', '5064', '2016-04-09 20:42:53', '18817800142', '2', '1');
INSERT INTO `verifycode` VALUES ('23', '4100', '2016-04-09 20:43:04', '18817800014', '1', '1');
INSERT INTO `verifycode` VALUES ('24', '2703', '2016-04-18 19:16:02', '18817800144', '2', '1');
INSERT INTO `verifycode` VALUES ('25', '4654', '2016-04-18 19:17:55', '18817800144', '2', '0');
INSERT INTO `verifycode` VALUES ('26', '3221', '2016-04-18 19:18:47', '18817800153', '1', '0');
INSERT INTO `verifycode` VALUES ('27', '4760', '2016-04-23 17:53:24', '18818223559', '1', '0');
INSERT INTO `verifycode` VALUES ('28', '7251', '2016-04-28 14:12:33', '18817801780', '1', '1');
INSERT INTO `verifycode` VALUES ('29', '8924', '2016-05-05 23:55:59', '18817800148', '2', '0');
INSERT INTO `verifycode` VALUES ('30', '5110', '2016-05-05 23:56:56', '18817800148', '2', '1');
INSERT INTO `verifycode` VALUES ('31', '8023', '2016-05-05 23:58:35', '18817800148', '2', '0');
INSERT INTO `verifycode` VALUES ('32', '5677', '2016-05-05 23:59:39', '18817800148', '2', '0');
INSERT INTO `verifycode` VALUES ('33', '3496', '2016-05-06 00:04:19', '18817800148', '2', '0');
INSERT INTO `verifycode` VALUES ('34', '2105', '2016-05-06 00:07:37', '18817800148', '2', '1');
INSERT INTO `verifycode` VALUES ('35', '3160', '2016-05-06 00:09:02', '18817800148', '2', '0');
INSERT INTO `verifycode` VALUES ('36', '8930', '2016-05-12 20:08:53', '18817800148', '2', '1');
INSERT INTO `verifycode` VALUES ('37', '7675', '2016-05-12 20:09:24', '18817800147', '2', '1');
INSERT INTO `verifycode` VALUES ('38', '2256', '2016-05-13 11:17:13', '18817800148', '2', '1');
INSERT INTO `verifycode` VALUES ('39', '6683', '2016-05-13 11:18:16', '18817800100', '1', '1');
INSERT INTO `verifycode` VALUES ('40', '8046', '2016-05-13 12:59:40', '18817800148', '2', '0');
INSERT INTO `verifycode` VALUES ('41', '7409', '2016-05-13 13:02:03', '18817800145', '2', '0');
INSERT INTO `verifycode` VALUES ('42', '0467', '2016-12-15 23:08:15', '13800000000', '1', '0');
