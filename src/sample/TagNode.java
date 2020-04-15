package sample;

import java.io.Serializable;

public class TagNode implements Serializable {

    private String tag;
    private String value;
    static final long serialVersionUID = 1L;

    public TagNode(String tag, String value){
        this.tag = tag;
        this.value = value;
    }

    public String getTag(){
        return tag;
    }

    public String getValue(){
        return value;
    }

    public boolean equals(Object o){

        if( o == null ){
            return false;
        }

        TagNode other = (TagNode)o;

        return this.tag.equals(other.tag) && this.value.equals(other.value);
    }

}
