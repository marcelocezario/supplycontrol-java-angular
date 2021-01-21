import { Fuel } from './fuel-enum.model';
import { Vehicle } from './../vehicle/vehicle.model';
export interface Supply {
    id?: number
    moment: string
    odometer: number
    litersFilled: number
    priceTotal: number
    fullTank: boolean
    fuel: Fuel
    vehicle: Vehicle
    totalJourneyFromFullTank?: number
    totalLitersWithTheJourney?: number
    averageConsumption?: number
}