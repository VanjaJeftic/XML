import { User } from 'src/app/models/user';
import { Zahtev } from './zahtev';
export class ZahtevBundleViewDTO {
    bundleID: number;
    user: User;
    bundleZahtevi: Zahtev[] = [];
}
