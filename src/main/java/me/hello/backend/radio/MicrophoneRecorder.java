package me.hello.backend.radio;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class MicrophoneRecorder {

    public static void main(String[] args) {
        // 오디오 형식 설정 (16비트, 16000Hz, 모노)
        AudioFormat format = new AudioFormat(16000, 16, 1, true, true);
        DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

        // 마이크 라인 열기
        try (TargetDataLine microphone = (TargetDataLine) AudioSystem.getLine(info)) {
            microphone.open(format);
            microphone.start();

            System.out.println("녹음 시작... Ctrl+C로 중지하십시오.");

            // 녹음 데이터를 파일로 저장
            Thread recordingThread = new Thread(() -> {
                try (AudioInputStream audioStream = new AudioInputStream(microphone)) {
                    File outputFile = new File("recorded_audio.wav");
                    AudioSystem.write(audioStream, AudioFileFormat.Type.WAVE, outputFile);
                    System.out.println("녹음이 완료되었습니다: " + outputFile.getAbsolutePath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            recordingThread.start();

            // 녹음 중지 대기
            System.in.read(); // 사용자가 Enter를 누를 때까지 대기
            microphone.stop();
            microphone.close();
        } catch (LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }
}
