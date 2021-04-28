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
    private static Scanner x;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{
        // TODO code application logic here
        //SlangWord();
        //SearchDefinition();
        //AddSlangWord();
        //DeleteSlangWord();
        //EditSlangWord();
        RandomSlangWord();
        /*DataInputStream dis;
        Scanner sc = new Scanner(System.in);
        int luachon = 1;
        while(luachon <= 11){
        System.out.println("1.Tìm kiếm theo slang word");
        System.out.println("2.Tìm kiếm theo definition");
        System.out.println("3.Hiển thị history");
        System.out.println("4.Add 1 slang words");
        System.out.println("5.Edit 1 slang word");
        System.out.println("6.Delete 1 slang word");
        System.out.println("7.Reset danh sách slang words gốc.");
        System.out.println("8.Random 1 slang word");
        System.out.println("9.Đố vui slang word");
        System.out.println("10.Đố vui definition");
        System.out.println("11.Thoat");
        System.out.print("Nhập lựa chọn: ");
        luachon = Integer.parseInt(sc.nextLine());
        
        switch (luachon) {
        case 1:
            ;
            continue;
        case 2:
            ;
            continue;
        case 3:
            ;
            continue;
        case 4:
            ;
            continue;
        default:
            break;
        }
        }*/
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
            
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(slangdictionary.size());
    }
    
    public static void SearchSlangWord() throws IOException{
        System.out.print("Nhập Slang Word cần tìm:");
        String slangword = sc.nextLine();
        
        System.out.println("definition : "+slangdictionary.get(slangword));
        
    }
    
    public static void SearchDefinition() throws IOException{
        System.out.print("Nhập definition cần tìm:");
        String definition = sc.nextLine();
        for(Entry<String, String> entry: slangdictionary.entrySet()) {
            //System.out.println(entry.getValue());
            if(entry.getValue().contains(definition)) {
                System.out.println("Slang Word : "+entry.getKey());
        }
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
            
            System.out.print("Nhap SlangWord muon xoa: ");
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
                sc.close();
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
            System.out.println("Khong tim thay Slangword " + removeTemp);
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
            
            System.out.print("Nhap Slangword muon sua: ");
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
                    System.out.print("Nhap Slangword:");
                    String newSlangWord = sc.nextLine();
            
                    System.out.print("Nhap Definition:");
                    String newDefinition = sc.nextLine();
                    
                    pw.println(newSlangWord+"`"+newDefinition);
                    count = 1;
                }
                else{
                    pw.println(currentLine);
                }
            }
            
            
            sc.close();
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
            System.out.println("Khong tim thay Slangword " + editTemp);
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
