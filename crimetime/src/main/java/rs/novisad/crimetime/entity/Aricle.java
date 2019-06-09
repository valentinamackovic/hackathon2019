package rs.novisad.crimetime.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity                
@Table(name="articles")
public class Aricle implements Serializable{

	@Id                                 
	@GeneratedValue(strategy=GenerationType.IDENTITY)  
	@Column(name="article_id", unique=true, nullable=false) 
	private int id;
	
	@Column(name="title", unique=false, nullable=false)
    private String title;
	
	@Column(name="url", unique=false, nullable=false)
    private String url;
	
	
	@Column(name="content", unique=false, nullable=false, columnDefinition = "longtext")
    private String content;
	
	@Enumerated(EnumType.STRING)
	@Column(name="crime_category", unique=false, nullable=false)
    private CrimeCategory crimeCategory;

    public Aricle(String title, String url, String content) {
        this.title = title;
        this.url = url;
        this.content = content;
    }

    public Aricle() {}

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CrimeCategory getCrimeCategory() {
		return crimeCategory;
	}

	public void setCrimeCategory(CrimeCategory crimeCategory) {
		this.crimeCategory = crimeCategory;
	}

	public String getTitle() {
        return title.toLowerCase();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content.toLowerCase();
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitleAndContent() {return (this.title + this.content).toLowerCase();}

    @Override
    public String toString() {
        return "Aricle{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", content='" + content + '\'' +
                ", crimeCategory=" + crimeCategory +
                '}';
    }
}
