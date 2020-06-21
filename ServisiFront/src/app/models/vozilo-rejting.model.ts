import { User } from './user';
import { Komentar } from './komentar';

export class VoziloRejting {
    id:number;
    user: User;
    markaVozila:string;
    modelVozila:string;
    klasaVozila:string;
    vrstaMenjaca:string;
    tipGoriva:string;
    predjeniKm:string;
    brsedistadeca:string;
    ocena : number;
    komentari:Komentar[];
}