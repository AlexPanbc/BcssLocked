package test.JavaCoreTest;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by LiuHuiChao on 2016/10/31.
 */
public class StringTest {
    @Test
    public void stringinternTest(){
        String name="漠漠水田飞白鹭";
        String name2=new String("漠漠水田飞白鹭").intern();
        System.out.println(name==name2);//true
    }

    @Test
    public void getData(){
        /*数据源*/
         List<TestData> sourceData=new ArrayList<TestData>();
        /*特殊类别*/
        final String categoryId="1";
        /*初始化数据源*/
        for(int i=0;i<10;i++){
            TestData testData=new TestData(String.valueOf(i),String.valueOf(i));
            if(i==Integer.parseInt(categoryId)){
                testData.setName("lllll");
                TestData testData2=new TestData(categoryId,"lalaal");
                sourceData.add(testData2);
            }
            sourceData.add(testData);
        }
        /*排序之后的数据*/
        TestData[] resultData=new TestData[sourceData.size()];
        /*临时存放特殊数据的list*/
        List<TestData> tempData=new ArrayList<TestData>();
        /*临时存放特殊数据位置的list*/
        List<Integer> indexData=new ArrayList<Integer>();
        /*初始化临时存放特殊数据的list*/
        for(TestData testData : sourceData){
            if(testData.getCategory().equals(categoryId)){
                tempData.add(testData);
            }
        }
        /*为每个特殊数据分配位置*/
        for(int i=0;i<tempData.size();i++){
            int index;
            index=SpecialDataFindIndex(sourceData.size(),indexData);
            tempData.get(i).setIndex(index);
            indexData.add(index);
        }
        /*现将特殊数据放入*/
        for(int i=0;i<tempData.size();i++){
            resultData[tempData.get(i).getIndex()]=tempData.get(i);
            sourceData.remove(tempData.get(i));
        }
        /*为剩下的元素分配位置*/
        for(int i=0;i<sourceData.size();i++){
            int index=DataFindIndex(sourceData.size()+tempData.size(),indexData);
            sourceData.get(i).setIndex(index);
            resultData[index]=sourceData.get(i);

        }

        for(int i=0;i<resultData.length;i++){
            if(resultData[i]!=null){
                System.out.println("第"+i+"个：getCategory=="+resultData[i].getCategory()+";getName=="+resultData[i].getName()+";getIndex=="+resultData[i].getIndex());
            }
        }

    }

    /*找到未被占用的index位置*/
    private int DataFindIndex(int range,List<Integer> takenIndexList){
        int index=new Random().nextInt(range);
        while(takenIndexList.contains(index)){
           return DataFindIndex(range,takenIndexList);
        }
        takenIndexList.add(index);
        return index;
    }

    /**
     * 为特殊数据寻找位置
     * @return
     */
    private int SpecialDataFindIndex(int range,List<Integer> takenIndexList){
        int index=new Random().nextInt(range);
        while(takenIndexList.contains(index) || takenIndexList.contains(index+1) || takenIndexList.contains(index-1)){
            return DataFindIndex(range,takenIndexList);
        }
        takenIndexList.add(index);
        return index;
    }

    /**
     * 模拟数据
     */
    class TestData{
        String category;
        String name;
        int index;

        public TestData(String category, String name) {
            this.category = category;
            this.name = name;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }
    }
}
