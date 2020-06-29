import { PrenormalizedTemplateMetadata } from '@angular/compiler';
import { EmailValidator } from '@angular/forms';

export class User {
  
    ime:string;
    prezime:string;
    adresa:string;
    mesto:string;
    telefon:number;
    id:number;
    nalogAktivan:boolean;
    username:string;
    password:string;
    potvrdalozinke:string;
    email:EmailValidator;

}
