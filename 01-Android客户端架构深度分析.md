# OldChat Android 客户端 — 架构深度分析

> 本章聚焦于 Android 客户端的 UI 架构、导航体系、自定义组件和 Fragment 管理。

---

## 一、Activity 清单与导航

### 1.1 完整 Activity 列表

根据 AndroidManifest.xml 注册的 Activity（共 60+），按功能模块分类：

| 模块 | Activity | 功能 |
|------|----------|------|
| **主框架** | `MainActivity` | 底部 Tab 主界面 |
| **认证** | `LoginActivity` | 登录 |
| | `RegisterActivity` | 注册 |
| | `RecoverPasswordActivity` | 找回密码 |
| | `ChangePasswordActivity` | 修改密码 |
| **聊天** | `ChatActivity` | 私聊界面 |
| | `GroupChatActivity` | 群聊界面 |
| | `ChatSearchActivity` | 聊天记录搜索 |
| | `ChatSettingsActivity` | 聊天设置 |
| **好友** | `AddFriendActivity` | 添加好友 |
| | `FriendRequestsActivity` | 好友请求列表 |
| **群组** | `GroupCreateActivity` | 创建群组 |
| | `GroupManageActivity` | 群管理设置 |
| | `GroupMembersActivity` | 群成员列表 |
| | `GroupAnnouncementActivity` | 群公告编辑 |
| | `GroupInviteActivity` | 邀请好友入群 |
| | `GroupJoinRequestsActivity` | 入群申请审批 |
| **朋友圈** | `MomentsActivity` | 动态广场 |
| | `MomentComposeActivity` | 发布动态 |
| | `MomentCommentsActivity` | 动态评论 |
| | `MomentGalleryActivity` | 动态图片浏览 |
| | `MomentNoticeActivity` | 动态通知 |
| | `UserSpaceActivity` | 用户个人空间 |
| **音乐** | `MusicPlazaActivity` | 音乐广场 |
| | `MusicPlayerActivity` | 音乐播放器 |
| | `MusicManageActivity` | 音乐管理 |
| | `MusicDownloadsActivity` | 已下载管理 |
| | `MusicCommentsActivity` | 音乐评论 |
| **表情** | `EmojiPickerActivity` | 表情选择器 |
| | `EmojiPlazaActivity` | 表情包广场 |
| **红包** | `RedPacketSendActivity` | 发红包 |
| | `RedPacketOpenActivity` | 开红包 |
| | `RedPacketDetailActivity` | 红包详情 |
| **设置** | `SettingsActivity` | 主设置 |
| | `ProfileEditActivity` | 个人资料编辑 |
| | `AccountManagementActivity` | 账号管理 |
| | `DeviceManagementActivity` | 设备管理 |
| | `NotificationSettingsActivity` | 通知设置 |
| | `DataSettingsActivity` | 数据/存储设置 |
| | `CacheSettingsActivity` | 缓存设置 |
| | `UISettingsActivity` | UI 设置 |
| | `PrivacyPolicyActivity` | 隐私政策 |
| | `FeedbackActivity` | 意见反馈 |
| **旧版视图** | `OldViewActivity` | 旧版视频列表 |
| | `OldViewVideoDetailActivity` | 旧版视频详情 |
| | `OldViewUpProfileActivity` | UP 主主页 |
| | `OldViewFavoritesActivity` | 收藏列表 |
| | `OldViewFavoriteDetailActivity` | 收藏夹详情 |
| | `OldViewHistoryActivity` | 观看历史 |
| **公堂** | `PublicCourtActivity` | 公堂/审判列表 |
| | `PublicCourtCaseDetailActivity` | 案件详情 |
| **其他** | `NotificationChatActivity` | 通知消息 |
| | `QRScanActivity` | 扫码 |
| | `QRCardActivity` | 我的二维码名片 |
| | `ImagePreviewActivity` | 图片预览 |
| | `CrashActivity` | 崩溃展示（独立进程） |

### 1.2 导航流程

```
App Launch
    │
    ▼
OldChatApplication.onCreate()
    │
    ▼
MainActivity.onCreate()
    │
    ├─ Token 有效 → 加载主界面（4 Tab）
    │                  ├─ ChatsFragment (聊天列表)
    │                  ├─ FriendsFragment (好友/群组)
    │                  ├─ DiscoverFragment (发现/朋友圈)
    │                  └─ ProfileFragment (我的)
    │
    └─ Token 无效 → LoginActivity
                        │
                        ├─ 登录成功 → MainActivity
                        └─ 注册 → RegisterActivity
```

## 二、Fragment 架构

### 2.1 主界面 Fragment

MainActivity 使用 ViewPager + FragmentPagerAdapter 管理四个 Tab，每个 Tab 是一个独立 Fragment：

| Fragment | 类引用 | 说明 |
|----------|--------|------|
| 聊天 | `m0.b` | 最近聊天列表，支持下拉刷新 |
| 好友 | `m0.e` | 好友列表 + 群组列表 |
| 发现 | `m0.c` | 朋友圈动态、音乐广场等入口 |
| 我的 | `k` | 个人主页、设置入口 |

### 2.2 Chat Activity 继承层次

聊天相关 Activity 的继承体系体现了清晰的重用设计：

```
AbstractActivityC0210a (私聊抽象基类)
    └── ChatActivity (私聊，最薄的一层，仅重写菜单回调)
    
AbstractActivityC0231w (群聊抽象基类)
    └── GroupChatActivity (群聊，最薄的一层)
```

基类 `AbstractActivityC0210a` / `AbstractActivityC0231w` 包含了：
- 消息列表 RecyclerView 的管理
- 输入框及附件面板（+ 按钮 → 图片/视频/文件/红包）
- 引用回复预览
- 阅后即焚计时器
- WebSocket 消息监听
- 消息长按上下文菜单

## 三、自定义 UI 组件体系

### 3.1 FontAwesomeTextView

扩展自 `TextView`，通过 `fontawesome-webfont.ttf` 字体文件渲染图标字符。
在布局中使用 Unicode 代码点（如 `` = 返回箭头）直接作为 text 属性。

### 3.2 TopStatusBar

自定义顶部状态栏组件，显示：
- 网络连接状态（在线/离线指示器）
- 未读消息数徽标
- 重连按钮（断线时）
- 单击/双击回调接口

### 3.3 TiltFrameLayout

带倾斜/按压特效的 FrameLayout 容器。用于 "+" 面板中的大图标按钮，按压时有微妙的 3D 倾斜动画，提升交互手感。

### 3.4 ActionPanelAnimatedLayout

聊天输入框的 "+" 附件面板容器。带展开/收起动画，包含：
- 图片/视频选择
- 位置分享
- 红包发送
- 表情包选择
- 文件传输

### 3.5 NoAnimViewPager

继承 `ViewPager`，支持控制页面切换是否带滚动动画。应用于 MainActivity 的 Tab 切换。

### 3.6 PastelChatRow / Pastel 风格组件

一系列以 "bg_pastel_*" 为前缀的 drawable 组件，构成一套独立的 UI 风格体系（pastel = 柔和的粉色系），可能是应用内的某种主题/皮肤系统。

## 四、RecyclerView 与消息列表

消息列表使用 `RecyclerView`，包含多种 Item Type：

| ViewType | 显示内容 |
|----------|----------|
| 文本消息（自己/对方） | 正文 + 时间 + 已读状态 |
| 图片消息 | 缩略图 + 加载进度 |
| 语音消息 | 波形图标 + 时长 + 未读红点 |
| 视频消息 | 封面图 + 播放按钮 |
| 文件消息 | 文件名 + 大小 + 下载按钮 |
| 红包消息 | 红包封面 + 标题 + 状态 |
| 系统消息 | 时间分隔符 / 群通知 / 撤回提示 |
| 引用回复 | 引用原文 + 当前回复 |
| 阅后即焚消息 | 焚毁倒计时 + 焚毁后提示 |

消息适配器支持：
- 头像显示
- 昵称颜色（群聊中不同成员不同颜色）
- 连续同人消息合并
- 消息长按弹出操作菜单（复制/转发/删除/引用/多选）

## 五、布局资源体系

应用使用大量自定义 drawable 资源，构成丰富的 UI 视觉效果。

### 5.1 布局文件命名规范

- `activity_*.xml` — 对应 Activity 的布局
- `fragment_*.xml` — 对应 Fragment 布局
- `item_*.xml` — RecyclerView 列表项
- `dialog_*.xml` — 对话框内容
- `header_*.xml` — 列表头部

### 5.2 Drawable 资源分类

| 前缀 | 用途 | 示例 |
|------|------|------|
| `bg_*` | 各种形状/渐变背景 | `bg_chat_bubble_self.xml` |
| `ic_*` | 图标 | `ic_action_image.xml` |
| `selector_*` | 状态选择器 | `selector_chat_item.xml` |
| `shape_*` | 基本形状 | `shape_circle.xml` |
| `anim_*` | 动画 | `square_activity_close_enter.xml` |

### 5.3 主题色系统

颜色定义在 `res/values/colors.xml` 中，核心颜色变量：

| 变量 | 用途 |
|------|------|
| `colorPrimary` | 主题主色 |
| `colorPrimaryDark` | 状态栏色 |
| `colorAccent` | 强调色 |
| `colorBackground` | 页面背景 |
| `colorToolbar` | 工具栏背景 |
| `colorTextPrimary` | 主要文字 |
| `colorTextSecondary` | 次要文字 |
| `colorDivider` | 分隔线 |
