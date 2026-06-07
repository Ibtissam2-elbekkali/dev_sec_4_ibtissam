package com.example.dev_sec_4.database;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes_ibtissam",
        foreignKeys = @ForeignKey(entity = User_ibtissam.class,
                                  parentColumns = "id_ibtissam",
                                  childColumns = "userId_ibtissam",
                                  onDelete = ForeignKey.CASCADE))
public class Note_ibtissam {
    @PrimaryKey(autoGenerate = true)
    private int id_ibtissam;
    
    private int userId_ibtissam;
    private String title_ibtissam;
    private String content_ibtissam;
    private long createdAt_ibtissam;
    
    public Note_ibtissam(int userId_ibtissam, String title_ibtissam, String content_ibtissam) {
        this.userId_ibtissam = userId_ibtissam;
        this.title_ibtissam = title_ibtissam;
        this.content_ibtissam = content_ibtissam;
        this.createdAt_ibtissam = System.currentTimeMillis();
    }
    
    public int getId_ibtissam() { return id_ibtissam; }
    public void setId_ibtissam(int id_ibtissam) { this.id_ibtissam = id_ibtissam; }
    
    public int getUserId_ibtissam() { return userId_ibtissam; }
    public void setUserId_ibtissam(int userId_ibtissam) { this.userId_ibtissam = userId_ibtissam; }
    
    public String getTitle_ibtissam() { return title_ibtissam; }
    public void setTitle_ibtissam(String title_ibtissam) { this.title_ibtissam = title_ibtissam; }
    
    public String getContent_ibtissam() { return content_ibtissam; }
    public void setContent_ibtissam(String content_ibtissam) { this.content_ibtissam = content_ibtissam; }
    
    public long getCreatedAt_ibtissam() { return createdAt_ibtissam; }
    public void setCreatedAt_ibtissam(long createdAt_ibtissam) { this.createdAt_ibtissam = createdAt_ibtissam; }
}
