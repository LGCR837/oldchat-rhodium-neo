# OldChat Android 客户端 — 技术文档索引

> 基于官方 APK v1.2.34 (com.im.oldchat) 反编译源码的深度静态分析
> 分析时间：2026-05-23

---

## 文档列表

| 编号 | 文档 | 内容 |
|------|------|------|
| 00 | [项目总览与架构](./00-项目总览与架构.md) | 项目定位、技术栈、整体架构、入口与生命周期 |
| 01 | [Android 客户端架构深度分析](./01-Android客户端架构深度分析.md) | Activity 清单、Fragment 体系、自定义 UI 组件、布局资源 |
| 02 | [网络层与 API 通信机制](./02-网络层与API通信机制.md) | HTTP 客户端、ECDH 加密、WebSocket、Token 管理、API 端点汇总 |
| 03 | [数据模型与存储系统](./03-数据模型与存储系统.md) | 核心数据模型（Message/User/Group/Moment）、持久化方案、数据流 |
| 04 | [多媒体与音乐系统](./04-多媒体与音乐系统.md) | MusicPlaybackService、缓存策略、音乐广场、资源上传 |
| 05 | [B站集成与扩展功能](./05-B站集成与扩展功能.md) | Bilibili API 集成、Old View、红包、公堂、表情包、签到 |
| 06 | [安全机制与加密体系](./06-安全机制与加密体系.md) | ECDH/AES-GCM/SpongyCastle、加密流程、安全降级、局限性分析 |
| 07 | [ECDH握手协议与加密流程详解](./07-ECDH握手协议与加密流程详解.md) | 握手协议精确规范、AES-CBC+HMAC双密钥派生、Payload格式、Python实现细节 |

## 分析范围

| 项目 | 值 |
|------|-----|
| 源文件 | 约 2500+ Java 文件（含 SpongyCastle 库） |
| 布局文件 | 153 个 XML 布局 |
| Drawable 资源 | 200+ 自定义 drawable |
| Activity 数量 | 60+ 个注册 Activity |
| Service 数量 | 3 个后台服务 |

## 关键发现

1. **端到端加密** — 所有非认证 API 使用 ECDH + AES-256-GCM 加密
2. **深度 B站 集成** — 内置完整的 Bilibili API 客户端
3. **丰富的 UI 组件** — 大量自定义 View 和 Drawable 资源
4. **老旧的兼容层** — minSdk=9，保留大量废弃 API 调用
5. **完整社交功能** — IM、朋友圈、音乐、红包、签到、公堂审判

## 源码目录说明

```
sources/
├── com/im/oldchat/          # 主包（应用入口 + MainActivity）
│   ├── bili/                # B站 API 集成
│   ├── service/             # 后台服务（消息/音乐/上传）
│   └── ui/                  # 所有 UI 界面
├── o0/                      # 工具类集合（混淆）
├── g0/                      # 网络层（混淆）
├── k/ / l0/ / c/            # UI 框架基类（混淆）
├── org/spongycastle/        # 完整加密库（2000+ 类）
├── android/support/         # Android Support 库
└── [A-Z0-9a-z]/            # 其他混淆包
```
