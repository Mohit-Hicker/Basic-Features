#include<stdio.h>
#include<stdlib.h>
int main(){
	FILE *fp;
	char str[20];
	printf("Enter any Message : ");
	gets(str);
	fp=fopen("f1.txt","w");
	if(fp==NULL){
		printf("File Not Found\n");
		exit(1);
	}
	
    fwrite(str,9,2,fp);
	fclose(fp);
	printf("\nAll Done...\nWriting Success...");
	return 0;
}
