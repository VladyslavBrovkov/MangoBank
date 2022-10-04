1 User
http://localhost:8888/user/delete
http://localhost:8888/user/details  (getUserById())

2 Account api
Admin
http://localhost:8888/account/getAll (List<AccountDto>)

http://localhost:8888/account/create
http://localhost:8888/account/update
http://localhost:8888/account/details  (AccountDto getAccountById())


2 Payment api
Admin
http://localhost:8888/payment/getAll (List<AccountDto>)

http://localhost:8888/payment/account/from/{accountFromId} ( @PathVariable accountFromId , return List<PaymentDto> )
http://localhost:8888/payment/account/to/{accountToId} ( @PathVariable accountToId , return List<PaymentDto> )

http://localhost:8888/payment/user/{userId} ( @PathVariable userId , return List<PaymentDto> )

http://localhost:8888/payment/create

http://localhost:8888/payment/details (PaymentDto getPaymentById())



===========
Example PathVariable
@GetMapping("/state/{key}")
public PagedQueryResult<SessionCourseDTO> listCoursesByState(@PathVariable String key, QueryListDTO queryListDTO) {
return service.listByState(key, queryListDTO.getPage(), queryListDTO.getLimit(), queryListDTO);
}



==== in future ===
1 create refund payment
