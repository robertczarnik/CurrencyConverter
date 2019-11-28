package pl.robertczarnik.currencyconverter.calls;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/calls")
@RestController
class CallController {

    private CallRepository callRepository;

    public CallController(CallRepository callRepository) {
        this.callRepository = callRepository;
    }

    @GetMapping("")
    List<Call> getAllRecords(){
        return callRepository.findAll();
    }
}