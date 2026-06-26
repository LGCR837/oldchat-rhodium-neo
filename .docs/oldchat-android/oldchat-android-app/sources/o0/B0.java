package o0;

import android.media.MediaCodec;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.media.MediaMetadataRetriever;
import android.media.MediaMuxer;
import java.io.File;
import java.nio.ByteBuffer;
import org.spongycastle.asn1.cmp.PKIFailureInfo;

/* JADX INFO: loaded from: classes.dex */
public abstract class B0 {
    public static boolean a(File file, File file2, long j2) throws Throwable {
        int integer;
        MediaExtractor mediaExtractor = new MediaExtractor();
        MediaMuxer mediaMuxer = null;
        try {
            mediaExtractor.setDataSource(file.getAbsolutePath());
            int trackCount = mediaExtractor.getTrackCount();
            if (trackCount <= 0) {
                try {
                    mediaExtractor.release();
                } catch (Exception unused) {
                }
                return false;
            }
            MediaMuxer mediaMuxer2 = new MediaMuxer(file2.getAbsolutePath(), 0);
            try {
                try {
                    int[] iArr = new int[trackCount];
                    int i2 = PKIFailureInfo.badCertTemplate;
                    for (int i3 = 0; i3 < trackCount; i3++) {
                        MediaFormat trackFormat = mediaExtractor.getTrackFormat(i3);
                        iArr[i3] = mediaMuxer2.addTrack(trackFormat);
                        if (trackFormat.containsKey("max-input-size") && (integer = trackFormat.getInteger("max-input-size")) > i2) {
                            i2 = integer;
                        }
                    }
                    MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
                    try {
                        mediaMetadataRetriever.setDataSource(file.getAbsolutePath());
                        String strExtractMetadata = mediaMetadataRetriever.extractMetadata(24);
                        if (strExtractMetadata != null) {
                            mediaMuxer2.setOrientationHint(Integer.parseInt(strExtractMetadata));
                        }
                    } catch (Exception unused2) {
                    } catch (Throwable th) {
                        try {
                            mediaMetadataRetriever.release();
                        } catch (Exception unused3) {
                        }
                        throw th;
                    }
                    try {
                        mediaMetadataRetriever.release();
                    } catch (Exception unused4) {
                    }
                    mediaMuxer2.start();
                    ByteBuffer byteBufferAllocate = ByteBuffer.allocate(i2);
                    MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
                    for (int i4 = 0; i4 < trackCount; i4++) {
                        mediaExtractor.selectTrack(i4);
                        mediaExtractor.seekTo(0L, 2);
                        while (true) {
                            int sampleData = mediaExtractor.readSampleData(byteBufferAllocate, 0);
                            if (sampleData < 0) {
                                break;
                            }
                            long sampleTime = mediaExtractor.getSampleTime();
                            if (j2 <= 0 || sampleTime <= j2) {
                                bufferInfo.offset = 0;
                                bufferInfo.size = sampleData;
                                bufferInfo.flags = mediaExtractor.getSampleFlags();
                                bufferInfo.presentationTimeUs = sampleTime;
                                mediaMuxer2.writeSampleData(iArr[i4], byteBufferAllocate, bufferInfo);
                                mediaExtractor.advance();
                            }
                        }
                        mediaExtractor.unselectTrack(i4);
                    }
                    try {
                        mediaExtractor.release();
                    } catch (Exception unused5) {
                    }
                    try {
                        mediaMuxer2.stop();
                    } catch (Exception unused6) {
                    }
                    try {
                        mediaMuxer2.release();
                        return true;
                    } catch (Exception unused7) {
                        return true;
                    }
                } catch (Exception unused8) {
                    mediaMuxer = mediaMuxer2;
                }
            } catch (Throwable th2) {
                th = th2;
                mediaMuxer = mediaMuxer2;
                try {
                    mediaExtractor.release();
                } catch (Exception unused9) {
                }
                if (mediaMuxer == null) {
                    throw th;
                }
                try {
                    mediaMuxer.stop();
                } catch (Exception unused10) {
                }
                try {
                    mediaMuxer.release();
                    throw th;
                } catch (Exception unused11) {
                    throw th;
                }
            }
        } catch (Exception unused12) {
        } catch (Throwable th3) {
            th = th3;
        }
        try {
            mediaExtractor.release();
        } catch (Exception unused13) {
        }
        if (mediaMuxer != null) {
            try {
                mediaMuxer.stop();
            } catch (Exception unused14) {
            }
            try {
                mediaMuxer.release();
            } catch (Exception unused15) {
            }
        }
        return false;
    }
}
