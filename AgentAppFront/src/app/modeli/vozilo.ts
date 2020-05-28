import { MarkaVozila } from './marka-vozila';
import { User } from './user';
import { KlasaVozila } from './klasa-vozila';
import { ModelVozila } from './model-vozila';
import { TipGoriva } from './tip-goriva';
import { VrstaMenjaca } from './vrsta-menjaca';
export class Vozilo {
    user: User;
    markaVozila: MarkaVozila;
    klasaVozila: KlasaVozila;
    modelVozila: ModelVozila;
    tipgoriva: TipGoriva;
    vrstamenjaca: VrstaMenjaca;
}
