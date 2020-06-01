import { Component, OnInit } from '@angular/core';
import {Vozilo} from 'src/app/models/vozilo';
import { NoviOglasService } from 'src/app/services/novi-oglas.service';


@Component({
  selector: 'novi-oglas',
  templateUrl: './novi-oglas.component.html',
  styleUrls: ['./novi-oglas.component.css']
})
export class NoviOglasComponent implements OnInit {

  public vozilo:Vozilo=new Vozilo();
  
  constructor(private noviOglasService:NoviOglasService) { }
  
  imageName: any;
  selectedFile: File;
  retrievedImage: any;
  message: string;
  urls=[];
  selectedFiles = [];

  ngOnInit() {
  }

  onSelect(e){
    if(e.target.files){
      for(let i=0; i<e.target.files.length; i++){
        let file = e.target.files[i];
        this.selectedFiles.push(file);
        let reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = (events:any) => {
          this.urls.push(events.target.result)
        }
        
      }
    }
  }

  // public onFileChanged(event) {
  //   this.selectedFile = event.target.files[0];
  // }

  public onSubmit(): void{
    event.preventDefault();
    console.log("Usao u onsubmit"+ this.vozilo.modelVozila );
    console.log(this.selectedFiles);
    // const uploadImageData = new FormData();
    // uploadImageData.append('imageFile', this.selectedFile, this.selectedFile.name);
    let res = this.noviOglasService.saveVozilo(this.vozilo, this.selectedFiles);
  }


/*
  onUpload() {
    console.log(this.selectedFile);
    
    //FormData API provides methods and properties to allow us easily prepare form data to be sent with POST HTTP requests.
    const uploadImageData = new FormData();
    uploadImageData.append('imageFile', this.selectedFile, this.selectedFile.name);
  
    //Make a call to the Spring Boot Application to save the image
    this.noviOglasService.post('http://localhost:8080/image/upload', uploadImageData, { observe: 'response' })
      .subscribe((response) => {
        if (response.status === 200) {
          this.message = 'Image uploaded successfully';
        } else {
          this.message = 'Image not uploaded successfully';
        }
      }
      );


  }
*/

}
