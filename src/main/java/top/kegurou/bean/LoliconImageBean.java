package top.kegurou.bean;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoliconImageBean {
    private int pid;
    private int p;
    private int uid;
    private String title;
    private String author;
    private String url;
    private boolean r18;
    private int width;
    private int height;
    private List<String> tags;
}