import '../components/common/Common.css';
import SubmitButton from "../components/common/SubmitButton"
import '../styles/Modal.css'
import KakaoPostcodeEmbed from 'react-daum-postcode';
import react, { useState } from "react"

export default function Modal({ openModal }) {
    const [isClose, setIsClose] = useState(false);
    if (!openModal) return null;
    const closeModalHandler =() =>{
        setIsClose(!isClose);
    };

    const handleComplete = (data) => {
        let fullAddress = data.address;
        let extraAddress = '';

        if (data.addressType === 'R') {
            if (data.bname !== '') {
                extraAddress += data.bname;
            }
            if (data.buildingName !== '') {
                extraAddress += extraAddress !== '' ? `, ${data.buildingName}` : data.buildingName;
            }
            fullAddress += extraAddress !== '' ? ` (${extraAddress})` : '';
        }

        console.log(fullAddress); // e.g. '서울 성동구 왕십리로2길 20 (성수동1가)'
    };

    return (
        <>
            {isClose ?
            null:
                < div className="Overlay" >
                    < main className="card-box modal-size" >
                        <KakaoPostcodeEmbed onComplete={handleComplete} />
                        <button
                            type="submit"
                            className="submit-button"
                            onClick={closeModalHandler}
                        >
                            <p className='button-text'>제출하기</p>
                        </button>
                    </main >
                </div >
            }
        </>
    )
}