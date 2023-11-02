package dangchph33497.fpoly.dangchph33497_assignment.ReadAndWrite;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Xfile {
    public static void ghi(Context context,String path,Object o){
        try {
            FileOutputStream fileOutputStream = context.openFileOutput(path,Context.MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(o);
            objectOutputStream.close();
            fileOutputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static Object doc(Context context,String path){
        Object list = null;
        try {
            FileInputStream fileInputStream =context.openFileInput(path);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            list = (ArrayList)objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
