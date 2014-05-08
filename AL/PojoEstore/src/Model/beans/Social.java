/**
 * Copyright (C) 2014 Diallo Alpha Oumar Binta && Ladoucette Arnaud
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package Model.beans;

/**
 *
 * @author Alpha Oumar Binta Diallo
 */
public class Social {

    @Override
    public String toString() {
        return "Social{" + "id=" + id + ", like=" +
                like + ", unlike=" + unLike + ", rev=" + rev + '}';
    }
    private String id;
    private int like;
    private int unLike;
    private String rev = "null";
	
    public Social(String id,int nbLike, int nbUnlike, String rev){
        this.id = id.replaceAll("\"", "");
	this.like = nbLike;
	this.unLike = nbUnlike;
	this.rev = rev.replaceAll("\"", "");
    }
    public Social(String id){
        this.id = id;
    }
	
    public int getLike() {
	return like;
    }
	
    public int getUnLike() {
	return unLike;
    }
	
    public String getRev() {
	return rev;
    }

    public String getId() {
	return id;
    }

    public void setId(String id) {
        this.id = id.replaceAll("\"", "");
    }

    public void setLike(int like) {
        this.like = like;
    }

    public void setUnLike(int unLike) {
        this.unLike = unLike;
    }

    public void setRev(String rev) {
        this.rev = rev.replaceAll("\"", "");
    }
    
    public void addLike(){
        setLike(getLike()+1);
    }
    public void addUnLike(){
        setUnLike(getUnLike()+1);
    }
	
}
