/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg20424095_slangdictionary;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

/**
 *
 * @author DELL
 */
public class Main {
    static Scanner sc = new Scanner(System.in);
    final static String filePath
        = "slang.txt";
    static HashMap<String, String> slangdictionary = new HashMap<String, String>();
    static HashMap<String, String> slanghistory = new HashMap<String, String>();
    private static Scanner x;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{
        // TODO code application logic here
        
        DataInputStream dis;
        Scanner sc = new Scanner(System.in);
        int luachon = 1;
        while(luachon <= 10){
        System.out.println("1.Tìm kiếm theo slang word || 2.Tìm kiếm theo definition || 3.Hiển thị history ||");
        System.out.println("4.Add 1 slang words || 5.Edit 1 slang word || 6.Delete 1 slang word ||");
        System.out.println("7.Reset danh sách slang words gốc || 8.Random 1 slang word ||");
        System.out.println("9.Đố vui slang word || 10.Đố vui definition ||");
        System.out.println("11.Thoat");
        System.out.print("Nhập lựa chọn: ");
        luachon = Integer.parseInt(sc.nextLine());
        
        switch (luachon) {
        case 1:
            SlangWord();
            SearchSlangWord();
            continue;
        case 2:
            SlangWord();
            SearchDefinition();
            continue;
        case 3:
            ShowHistory();
            continue;
        case 4:
            AddSlangWord();
            continue;
        case 5:
            EditSlangWord();
            continue;
        case 6:
            DeleteSlangWord();
            continue;
        case 7:
            System.out.println("Coming soon!!!");
            continue;
        case 8:
            RandomSlangWord();
            continue;
        case 9:
            System.out.println("Coming soon!!!");
            continue;
        case 10:
            System.out.println("Coming soon!!!");
            continue;
        default:
            break;
        }
        }
    }
    
    public static void SlangWord() throws IOException{
        
        try {
            File file = new File(filePath);
            BufferedReader br = new BufferedReader(new FileReader(file));        
            String line = null;
  
            while ((line = br.readLine()) != null) {

			String[] parts = line.split("`");
                        if (parts.length < 2)
			  continue;
			//if (!str[0].equals("") && !str[1].equals(""))
			slangdictionary.put(parts[0], parts[1]);

           }       
        br.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(slangdictionary.size());
    }
    
    public static void SearchSlangWord() throws IOException{
        File SlangHistory = new File("history.txt");
        BufferedReader br = new BufferedReader( new InputStreamReader(System.in, "utf8"));
        BufferedWriter bw = new BufferedWriter( new FileWriter(SlangHistory,true));
        PrintWriter pw = new PrintWriter(bw);
        
        System.out.print("Nhập Slang Word cần tìm:");
        String slangword = sc.nextLine();
        
        System.out.println("definition : "+slangdictionary.get(slangword));
        pw.println(slangword+"`"+slangdictionary.get(slangword));
        pw.flush();
        pw.close();
    }
    
    public static void SearchDefinition() throws IOException{
        File SlangHistory = new File("history.txt");
        BufferedReader br = new BufferedReader( new InputStreamReader(System.in, "utf8"));
        BufferedWriter bw = new BufferedWriter( new FileWriter(SlangHistory,true));
        PrintWriter pw = new PrintWriter(bw);
        
        System.out.print("Nhập definition cần tìm:");
        String definition = sc.nextLine();
        for(Entry<String, String> entry: slangdictionary.entrySet()) {
            //System.out.println(entry.getValue());
            if(entry.getValue().contains(definition)) {
                System.out.println("Slang Word : "+entry.getKey()+", Definition: "+ slangdictionary.get(entry.getKey()));
                pw.println(entry.getKey()+"`"+slangdictionary.get(entry.getKey()));
            }   
        }
        pw.flush();
        pw.close();
        
    }
    
    public static void ShowHistory() throws IOException{
        File SlangHistory = new File("history.txt");
        String slangword=""; String definition="";
        String Confirm = "";
        System.out.println("---------Hitory Search-----"); 
         
         try{
            x = new Scanner (new File("history.txt"));
            x.useDelimiter("[`\n]");
             while (x.hasNext()) {
                slangword=x.next();
                definition = x.next();
                               
                System.out.println("Slang word: "+slangword+ ", Definition: "+definition);
                //JOptionPane.showMessageDialog(null,"Ho Ten: "+hoten+",Diem: "+Diem+",Dia Chi: "+DiaChi+",Ghi Chu: "+Ghichu);
             }
            x.close();
            while(!Confirm.equals("y")){
            System.out.print("Bạn muốn xóa lịch sử(y/n):");
            Confirm = sc.nextLine();
            if(Confirm.equals("y")){
                if(SlangHistory.delete()){
                    System.out.println("Lịch sử search đã bị xóa!!!");
                }
                else{
                    System.out.println("failed");
                }
            }
            else if(Confirm.equals("n")){
                break;
            }
            }
                
        }catch(Exception e){
            System.out.println("Hiện không có lịch sử search");
        }
        
        
    }
    
    public static void AddSlangWord()throws IOException  {
        BufferedReader br = new BufferedReader( new InputStreamReader(System.in, "utf8"));
        BufferedWriter bw = new BufferedWriter( new FileWriter(filePath,true));
        PrintWriter pw = new PrintWriter(bw);
        DataOutputStream dos;
        
	try 
	{   
            System.out.println("---------Add Slang Word-----");
            System.out.print("Slang Word:");
            String SlangWord = br.readLine();
            
            System.out.print("Definition:");
            String Definition = br.readLine();
        
            pw.println(SlangWord+"`"+Definition);
            pw.flush();
            pw.close();
	}
	catch(IOException exc) 
	{
		System.out.println("Error reading file.");
	}
		
		//dos.close();
                System.out.println("Finished ! Check your file !");
    }
    
    
    public static void DeleteSlangWord()throws IOException  
	{
            File oldFile = new File(filePath);
            File newFile = new File("temp.txt");
            boolean found = false;
            String Definition="";String SlangWord = "";
            String Confirm = "";
            String removeTemp = "";
                    
            int count=0;
            String currentLine;
            String data[];
         
         try{
            System.out.println("---------Delete Slang Word-----");
            FileWriter fw = new FileWriter("temp.txt",true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);
            
            System.out.print("Nhập SlangWord muốn xóa: ");
            removeTemp =sc.nextLine();
            while(Confirm != "y"){
                System.out.print("Bạn có muốn xóa Slang Word(" + removeTemp +")không(y/n):");
                Confirm = sc.nextLine();
                if(Confirm.equals("y")){
                //sc = new Scanner (new File(filePath));
                //sc.useDelimiter("[`\n]");
                while ((currentLine = br.readLine())!= null) {
                    /*SlangWord=sc.next();
                    Definition = sc.next();
                    if(!SlangWord.equals(removeTemp))
                    {
                        pw.println(SlangWord+"`"+Definition);
                    }*/
                    
                    data = currentLine.split("`");
                    if(!(data[0].equalsIgnoreCase(removeTemp))){
                        pw.println(currentLine);
                    }
                    
                    else{
                        count = 1;
                    }
                }
                
                pw.flush();
                pw.close();
                fr.close();
                br.close();
                bw.close();
                fw.close();
                oldFile.delete();
                File dump = new File(filePath);
                newFile.renameTo(dump);
                break;
                }
                else if(Confirm.equals("n")){
                    break;
                }
            }
        }catch(Exception e){
            System.out.println("");
        }
        if(Confirm.equals("y") && count == 1){
            System.out.println("Finished ! Check your file !");
        }
        else if(count == 0){
            System.out.println("Không tìm thấy Slangword " + removeTemp);
        }
    }
    
    public static void EditSlangWord()throws IOException  
	{
            File oldFile = new File(filePath);
            File newFile = new File("temp.txt");
            boolean found = false;
            String Definition="";String SlangWord = "";
            int count=0;
            
            String editTemp = "";
            String currentLine;
            String data[];
         try{
            System.out.println("---------Edit Slang Word-----");
            FileWriter fw = new FileWriter("temp.txt",true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);
            
            System.out.print("Nhập Slangword muốn sửa: ");
            editTemp =sc.nextLine();
            
            
            //dos.writeUTF(hoten);
        
            
            //x = new Scanner (new File(filePath));
            //x.useDelimiter("[`\n]");
            while ((currentLine = br.readLine())!= null) {
                /*SlangWord=x.next();
                Definition = x.next();*/
                data = currentLine.split("`");
                if((data[0].equalsIgnoreCase(editTemp)))
                {
                    System.out.print("Nhập Slangword:");
                    String newSlangWord = sc.nextLine();
            
                    System.out.print("Nhập Definition:");
                    String newDefinition = sc.nextLine();
                    
                    pw.println(newSlangWord+"`"+newDefinition);
                    count = 1;
                }
                else{
                    pw.println(currentLine);
                }
            }
            
            
            //sc.close();
            pw.flush();
            pw.close();
            fr.close();
            br.close();
            bw.close();
            fw.close();
            
            oldFile.delete();
            File dump = new File(filePath);
            newFile.renameTo(dump);
        }catch(Exception e){
            System.out.println("");
        }
        if(count == 0){
            System.out.println("Không tìm thấy Slangword " + editTemp);
        }
        else{
            System.out.println("Finished ! Check your file !");
        }
    }
    
    public static void RandomSlangWord()throws IOException {
        System.out.println("-------------Random Slang Word---------------");
        String data[];
        File file = new File(filePath); 
        final RandomAccessFile f = new RandomAccessFile(file, "r");
        final long randomLocation = (long) (Math.random() * f.length());
        f.seek(randomLocation);
        f.readLine();
        String randomLine = f.readLine();
        f.close();
        
        data = randomLine.split("`");
        System.out.println("Slang word: " + data[0] + ", Definition: " + data[1]);
    }
}
