class HelloWorld{  
    public static void main(String args[]){  
        int[] arr = new int[5];

        for (int i = 0; i < arr.length; i++) {
            System.out.println(String.format("%d", arr[i]));
        }

        System.out.println("Hello Java");  
    }
}
