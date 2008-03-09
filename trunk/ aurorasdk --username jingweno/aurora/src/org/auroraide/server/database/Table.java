package org.auroraide.server.database;

import java.sql.*;
import java.lang.reflect.*;
import java.util.ArrayList;


public class Table{

	int id;

	public String getTableName(){
		String className=getClass().getName();
		className=className.substring(className.lastIndexOf(".")+1);
		return className;
	}

	public String toString(){
		String s="["+getTableName()+"] id="+id;
		try{
			Field[] fields=getClass().getFields();
			for (int i=0; i<fields.length; i++){
				s+=", "+fields[i].getName()+" = ";
				if (fields[i].get(this)==null) s+="null";
				else s+=fields[i].get(this);
			}
		}
		catch(IllegalAccessException e){
			e.printStackTrace();
		}
		return s;
	}

	public boolean save() throws Exception{
		DAO dao=DAO.getDAO();
		//PreparedStatement
		Field[] fields=getClass().getFields();
		String q1="",q="";
		String className=getTableName();
		try{
			if (id==0){
				for (int i=0,j=0; i<fields.length; i++){
					if (fields[i].getName().equals("id")) continue;
					if (fields[i].get(this)==null) continue;
					if (j>0){
						q+=",";
						q1+=",";
					}
					j++;
					q+=fields[i].getName();
					q1+="?";
				}
				q="INSERT INTO "+className+" (" +q+ ") values (" +q1+ ")";
				System.out.println("q = "+q);
				PreparedStatement p=dao.conn.prepareStatement(q);
				for (int i=0,j=0; i<fields.length; i++){
					if (fields[i].getName().equals("id")) continue;
					if (fields[i].get(this)==null) continue;
					p.setObject(j+1,fields[i].get(this));//fields[i].getType().newInstance()));
					j++;
				}
				p.executeUpdate();
				q="SELECT max(id) from "+className;
				Statement stmt=dao.conn.createStatement();
				ResultSet rs=stmt.executeQuery(q);
				if (rs.next())
					id=rs.getInt(1);
				stmt.close();
				p.close();
			}
			else{
				for (int i=0,j=0; i<fields.length; i++){
					if (fields[i].getName().equals("id")) continue;
					if (j>0) q+=",";
					j++;
					q+=fields[i].getName()+"=?";
				}
				q="UPDATE "+className+" SET " +q+ " WHERE id=?";
				System.out.println("q = "+q);
				PreparedStatement p=dao.conn.prepareStatement(q);
				for (int i=0,j=0; i<fields.length; i++){
					if (fields[i].getName().equals("id")) continue;
					if (fields[i].get(this)==null)
						p.setObject(j+1,"null");
					else
						p.setObject(j+1,fields[i].get(this));
					j++;
				}
				p.setInt(fields.length+1,id);
				p.executeUpdate();
				p.close();
			}
		}
		catch(IllegalAccessException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean load(int id) throws Exception{
		DAO dao=DAO.getDAO();
		Field[] fields=getClass().getFields();
		String q="";
		String className=getTableName();
		if (id!=0){
			for (int i=0,j=0; i<fields.length; i++){
				if (fields[i].getName().equals("id")) continue;
				if (j>0) q+=",";
				j++;
				q+=fields[i].getName();
			}
			q="SELECT "+q+" FROM "+className+ " where id=?";
			System.out.println("q = "+q);
			PreparedStatement p=dao.conn.prepareStatement(q);
			p.setObject(1,id);
			ResultSet rs=p.executeQuery();
			p.close();
			if (rs.next()){
				for (int i=0,j=0; i<fields.length; i++){
					if (fields[i].getName().equals("id")) continue;
					fields[i].set(this,rs.getObject(j+1));
				}
			}
			else{
				return false;
			}
			
		}
		else{
			return false;
		}
		
		return true;
	}
	
	public ArrayList<Table> loadAll(String[] keys, Object[] values) throws Exception{
		DAO dao=DAO.getDAO();
		Field[] fields=getClass().getFields();
		String q1="",q="id";
		String className=getTableName();
		for (int i=0; i<fields.length; i++){
			q += ","+fields[i].getName();
		}
		if (keys.length!=values.length) throw new Exception("Number of fields and number of values must be equal");
		for (int i=0; i<keys.length && i<values.length; i++){
			if (i>0) q1 += " AND ";
			if (values[i]==null)
				q1 += keys[i]+" IS NULL ";
			else
				q1 += keys[i] +" =?";
		}
		q="SELECT " + q + " FROM " + className;
		if (keys.length>0) q += " WHERE " + q1;
		System.out.println("q = "+q);
		PreparedStatement p=dao.conn.prepareStatement(q);
		for (int i=0,j=1; i<keys.length && i<values.length; i++){
			if (values[i]!=null)
				p.setObject(j++,values[i]);
		}
		ResultSet rs= p.executeQuery();
		ArrayList<Table> list=new ArrayList<Table>();
		while(rs.next()){
			Table tab=this.getClass().newInstance();//clone();
			tab.id=rs.getInt(1);
			for (int i=0; i<fields.length; i++){
				if(!fields[i].getType().isAssignableFrom(Blob.class))
					fields[i].set(tab,rs.getObject(i+2));
				else
					fields[i].set(tab,rs.getBlob(i+2));
				
			}
			list.add(tab);
		}
		p.close();
		return list;
	}
	
	public ArrayList<?> loadAll() throws Exception{
		return loadAll(new String[]{},new Object[]{});
	}	
	
	/*public boolean checkExists(ArrayList<String> keys, ArrayList values) throws Exception{
		b.DAO dao=b.DAO.getDAO();
		String q1="",q="";
		String className=getTableName();
		if (keys.size()!=values.size()) throw new Exception();
		
		for (int i=0; i<keys.size(); i++){
			if (i>0) q+=" AND ";
			if (values.get(i)==null)
				q+=keys.get(i)+" IS NULL ";
			else
				q+=keys.get(i)+"=?";
		}
		q="SELECT id FROM "+className+" WHERE " +q;
		System.out.println("q = "+q);
		PreparedStatement p=dao.conn.prepareStatement(q);
		for (int i=0,j=1; i<values.size(); i++){
			if (values.get(i)!=null)
				p.setObject(j++,values.get(i));
		}
		ResultSet rs=p.executeQuery();
		if (rs.next()) return true;
		else return false;
	}*/

	public boolean load() throws Exception{
		return load(id);
	}
	
	public boolean delete(int id) throws Exception{
		DAO dao=DAO.getDAO();
		//Field[] fields=getClass().getFields();
		String q="";
		String className=getTableName();
		if (id!=0){
			q="DELETE FROM "+className+ " where id=?";
			System.out.println("q = "+q);
			PreparedStatement p=dao.conn.prepareStatement(q);
			p.setObject(1,id);
			int ret=p.executeUpdate();
			p.close();
			id=0;
			return (ret>0);
		}
		else{
			return false;
		}
	}
	
	public boolean delete() throws Exception{
		return delete(id);
	}
	
	public int deleteAll(String[] keys, Object[] values) throws Exception{
        DAO dao=DAO.getDAO();
        String q="",q1="";
        String className=getTableName();
        if (keys.length!=values.length) throw new Exception("Number of fields and number of values must be equal");
        for (int i=0; i<keys.length && i<values.length; i++){
            if (i>0) q1 += " AND ";
            if (values[i]==null)
                q1 += keys[i]+" IS NULL ";
            else
                q1 += keys[i] +" =?";
        }
        q="DELETE FROM " + className;
        if (keys.length>0) q += " WHERE " + q1;
        System.out.println("q = "+q);
        PreparedStatement p=dao.conn.prepareStatement(q);
        for (int i=0,j=1; i<keys.length && i<values.length; i++){
            if (values[i]!=null)
                p.setObject(j++,values[i]);
        }
        int count=p.executeUpdate();
        p.close();
        return count;
    }
     
    public int deleteAll() throws Exception{
        return deleteAll(new String[]{},new Object[]{});

    }     
	
	
}