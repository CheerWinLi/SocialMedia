<template>
    <div>
        <h1>视频</h1>
        <el-row>
            <el-col :span="24">
                <!-- 视频展示区域 -->
                <el-card class="video-box">
                    <div class="video-container">
                        <video
                            :src="currentVideo.src"
                            controls
                            class="video-player"
                        >
                            您的浏览器不支持 video 标签。
                        </video>
                        <div v-if="loading" class="loading">
                            加载中...
                        </div>
                        <div class="video-actions">
                            <el-button type="text" @click="prevVideo">上一个</el-button>
                            <el-button type="text" @click="nextVideo">下一个</el-button>
                            <el-button
                                type="text"
                                :class="{ active: currentVideo.liked }"
                                @click="toggleLike"
                            >
                                点赞 ({{ currentVideo.likes }})
                            </el-button>
                            <el-button
                                type="text"
                                :class="{ active: currentVideo.commented }"
                                @click="toggleComment"
                            >
                                评论 ({{ currentVideo.comments }})
                            </el-button>
                            <el-button
                                type="text"
                                :class="{ active: currentVideo.collected }"
                                @click="toggleCollect"
                            >
                                收藏 ({{ currentVideo.collects }})
                            </el-button>
                        </div>
                    </div>
                </el-card>
            </el-col>
        </el-row>
    </div>
</template>

<script>
export default {
    data() {
        return {
            videos: [], // 视频列表
            currentVideoIndex: 0, // 当前播放的视频索引
            loading: true // 视频是否正在加载
        };
    },
    mounted() {
        this.fetchVideos();
    },
    methods: {
        fetchVideos() {
            // 模拟获取视频列表的请求
            setTimeout(() => {
                this.videos = [
                    { src: 'https://www.w3schools.com/html/mov_bbb.mp4', likes: 0, liked: false, comments: 0, commented: false, collects: 0, collected: false },
                    { src: 'https://www.w3schools.com/html/movie.mp4', likes: 0, liked: false, comments: 0, commented: false, collects: 0, collected: false },
                    { src: 'https://www.w3schools.com/html/mov_bbb.mp4', likes: 0, liked: false, comments: 0, commented: false, collects: 0, collected: false }
                ]; // 示例视频列表
                this.loading = false; // 视频加载完成后隐藏加载提示
            }, 1000); // 模拟网络延迟
        },
        prevVideo() {
            if (this.currentVideoIndex > 0) {
                this.currentVideoIndex--;
            }
        },
        nextVideo() {
            if (this.currentVideoIndex < this.videos.length - 1) {
                this.currentVideoIndex++;
            }
        },
        toggleLike() {
            const currentVideo = this.videos[this.currentVideoIndex];
            if (currentVideo.liked) {
                this.unlikeVideo(currentVideo);
            } else {
                this.likeVideo(currentVideo);
            }
        },
        toggleComment() {
            const currentVideo = this.videos[this.currentVideoIndex];
            if (currentVideo.commented) {
                this.deleteComment(currentVideo);
            } else {
                this.addComment(currentVideo);
            }
        },
        toggleCollect() {
            const currentVideo = this.videos[this.currentVideoIndex];
            if (currentVideo.collected) {
                this.removeCollect(currentVideo);
            } else {
                this.addCollect(currentVideo);
            }
        },
        likeVideo(video) {
            this.simulateRequest(() => {
                video.liked = true;
                video.likes++;
            });
        },
        unlikeVideo(video) {
            this.simulateRequest(() => {
                video.liked = false;
                video.likes--;
            });
        },
        addComment(video) {
            this.simulateRequest(() => {
                video.commented = true;
                video.comments++;
            });
        },
        deleteComment(video) {
            this.simulateRequest(() => {
                video.commented = false;
                video.comments--;
            });
        },
        addCollect(video) {
            this.simulateRequest(() => {
                video.collected = true;
                video.collects++;
            });
        },
        removeCollect(video) {
            this.simulateRequest(() => {
                video.collected = false;
                video.collects--;
            });
        },
        simulateRequest(callback) {
            // 模拟请求
            setTimeout(() => {
                const statusCode = Math.random() < 0.8 ? 200 : 400; // 模拟请求成功或失败
                if (statusCode === 200) {
                    callback();
                }
            }, 500); // 模拟请求延迟
        }
    },
    computed: {
        currentVideo() {
            return this.videos[this.currentVideoIndex] || {};
        }
    }
};
</script>

<style scoped>
.video-box {
    height: 100%;
}
.video-container {
    position: relative;
    width: 100%;
    height: 100%;
}
.video-player {
    display: block;
    width: 100%;
    height: 100%;
}
.video-actions {
    position: absolute;
    top: 10px;
    right: 10px;
    display: flex;
    flex-direction: column;
    gap: 10px;
}
.video-actions .active {
    color: #f00; /* 可以根据需要设置激活状态的样式 */
}
.loading {
    display: none;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    font-size: 18px;
}
</style>
