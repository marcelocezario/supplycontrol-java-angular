import { VehicleModel } from './../vehiclemodel/vehiclemodel.model';
export interface Vehicle {
    id?: number
    licensePlate: string
    modelYear: number
    tankCapacity: number
    vehicleModel: VehicleModel
}